package br.com.tiagoiwamoto.iwtrecibos.core.usecase.usuario;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/03/2022 | 20:57
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import br.com.tiagoiwamoto.iwtrecibos.core.service.auth.JwtService;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.UsuarioService;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.JwtResponse;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UsuarioAuthentecateUsecase {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtResponse createJwtResponse(UsuarioDto usuarioDto){

        var authentication = new UsernamePasswordAuthenticationToken(usuarioDto.getUsername(), usuarioDto.getPassword());
        var authenticatedUser = this.authenticationManager.authenticate(authentication);
        Usuario usuario = this.usuarioService.recuperarUsuarioPeloUsername(usuarioDto.getUsername());
        var userId = usuario.getUuid();
        return createJwtResponse(usuarioDto.getUsername(), authenticatedUser, userId);
    }

    public JwtResponse createJwtResponse(String refreshToken) {
        String username = this.jwtService.getUserFromRefreshToken(refreshToken);
        Usuario usuario = this.usuarioService.recuperarUsuarioPeloUsername(username);
        var userId = usuario.getUuid();
        String password = usuario.getPassword();

        Authentication authenticatedUser = new UsernamePasswordAuthenticationToken(username, password);

        return createJwtResponse(username, authenticatedUser, userId);
    }

    private JwtResponse createJwtResponse(String username, Authentication authenticatedUser, String userId) {
        var token = this.jwtService.generateToken(authenticatedUser);
        LocalDateTime experesAt = this.jwtService.getExpirationFromToken(token);
        String refreshToken = jwtService.generateRefreshToken(username);

        return new JwtResponse(token, userId, refreshToken, "Bearer", experesAt);
    }
}
