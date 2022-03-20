package br.com.tiagoiwamoto.iwtrecibos.core.service.auth;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/03/2022 | 20:45
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.AuthenticateUser;
import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuarioLocalizado = this.usuarioService.recuperarUsuarioPeloUsername(username);
        return new AuthenticateUser(usuarioLocalizado);
    }
}
