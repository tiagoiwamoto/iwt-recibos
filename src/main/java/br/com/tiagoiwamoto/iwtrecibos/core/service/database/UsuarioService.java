package br.com.tiagoiwamoto.iwtrecibos.core.service.database;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 20:03
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import br.com.tiagoiwamoto.iwtrecibos.core.exception.ComunicacaoComBaseDeDadosException;
import br.com.tiagoiwamoto.iwtrecibos.core.exception.UsuarioNaoEncontradoException;
import br.com.tiagoiwamoto.iwtrecibos.core.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario gravarNovoUsuario(Usuario usuario){
        try{
            log.info("Iniciando gravação de um novo usuário, {}", usuario);
            var usuarioSalvo = this.usuarioRepository.save(usuario);
            log.info("usuário gravado no banco de dados com sucesso, {}", usuarioSalvo);
            return usuarioSalvo;
        }catch (Exception e){
            log.error("Falha ao gravar no banco de dados, {}", usuario);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public Usuario recuperarUsuarioPorId(Long id){
        try{
            log.info("Iniciando busca de um usuário por id, {}", id);
            var usuario = this.usuarioRepository.findById(id);
            log.info("usuário gravado no banco de dados com sucesso, {}", usuario);
            return usuario.orElseThrow(UsuarioNaoEncontradoException::new);
        }catch (Exception e){
            log.error("Falha ao gravar no banco de dados, {}", id);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public Usuario recuperarUsuarioPorUuid(String uuid){
        try{
            log.info("Iniciando busca de um usuário por uuid, {}", uuid);
            var usuario = this.usuarioRepository.findByUuid(uuid);
            log.info("usuário localizado no banco de dados com sucesso, {}", usuario);
            return usuario.orElseThrow(UsuarioNaoEncontradoException::new);
        }catch (Exception e){
            log.error("Falha ao localizado no banco de dados, {}", uuid);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public Usuario recuperarUsuarioPeloUsername(String username){
        try{
            log.info("Iniciando busca de um usuário por username, {}", username);
            var usuario = this.usuarioRepository.findByUsername(username);
            log.info("usuário localizado no banco de dados com sucesso, {}", usuario);
            return usuario.orElseThrow(UsuarioNaoEncontradoException::new);
        }catch (Exception e){
            log.error("Falha ao localizar no banco de dados, {}", username);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

}
