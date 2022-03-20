package br.com.tiagoiwamoto.iwtrecibos.core.usecase.usuario;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 20:21
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import br.com.tiagoiwamoto.iwtrecibos.core.mapper.UsuarioMapper;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.UsuarioService;
import br.com.tiagoiwamoto.iwtrecibos.core.util.Encript;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.UsuarioDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
@Slf4j
public class UsuarioCreateUsecase {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDto criarNovoUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = this.usuarioMapper.toUsuarioEntity(usuarioDto);
        log.info("Convertendo para entidade de usuário, {}", usuario);
        usuario.comDataCriacao();
        usuario.setPassword(this.passwordEncoder.encode(usuarioDto.getPassword()));
        usuario.gerarUuid();
        usuario = this.usuarioService.gravarNovoUsuario(usuario);
        usuarioDto = this.usuarioMapper.toUsuarioDto(usuario);
        log.info("Convertendo para o dto de usuário, {}", usuarioDto);
        return usuarioDto;
    }



}
