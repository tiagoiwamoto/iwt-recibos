package br.com.tiagoiwamoto.iwtrecibos.core.usecase.pessoa;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 05/02/2022 | 17:18
 */

import br.com.tiagoiwamoto.iwtrecibos.core.mapper.PessoaMapper;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.PessoaService;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.UsuarioService;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.PessoaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PessoaRecoveryUsecase {

    private final PessoaService pessoaService;
    private final UsuarioService usuarioService;
    private final PessoaMapper pessoaMapper;

    public List<PessoaDto> recuperarPessoasDeUmUsuario(String uuuidUsuario){
        var usuario = this.usuarioService.recuperarUsuarioPorUuid(uuuidUsuario);
        var pessoas = this.pessoaService.buscaPessoasPorUsuario(usuario);
        var pessoasDto = pessoas.stream().map(this.pessoaMapper::toPessoaDto).collect(Collectors.toList());
        log.info("Convertendo para o dto de usuários, {}", pessoasDto);
        return pessoasDto;
    }
}
