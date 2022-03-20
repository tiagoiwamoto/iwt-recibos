package br.com.tiagoiwamoto.iwtrecibos.entrypoint;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 02/02/2022 | 20:00
 */

import br.com.tiagoiwamoto.iwtrecibos.config.rest.ApiResponseDto;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.usuario.UsuarioAuthentecateUsecase;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.usuario.UsuarioCreateUsecase;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.JwtResponse;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/usuarios")
@RequiredArgsConstructor
@Slf4j
public class UsuarioResource {

    private final UsuarioCreateUsecase usuarioCreateUsecase;
    private final UsuarioAuthentecateUsecase usuarioAuthentecateUsecase;

    @PostMapping
    public ResponseEntity<ApiResponseDto<UsuarioDto>> create(@RequestBody UsuarioDto usuarioDto) throws NoSuchAlgorithmException {
        log.info("iniciando chamada no recurso POST /usuarios");
        var usuario = this.usuarioCreateUsecase.criarNovoUsuario(usuarioDto);
        var response = ResponseEntity.created(URI.create("")).body(ApiResponseDto.of(HttpStatus.CREATED.name(), usuario, ""));
        log.info("criação de um novo usuário concluída com sucesso, {}", response);
        return response;
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(this.usuarioAuthentecateUsecase.createJwtResponse(usuarioDto));
    }

    @PostMapping(path = "/refresh/{refreshToken}")
    public ResponseEntity<JwtResponse> refreshToken(@PathVariable("refreshToken") String refreshToken){
        return ResponseEntity.ok(this.usuarioAuthentecateUsecase.createJwtResponse(refreshToken));
    }

}
