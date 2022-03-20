package br.com.tiagoiwamoto.iwtrecibos.core.usecase.pessoa;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 03/02/2022 | 07:34
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Pessoa;
import br.com.tiagoiwamoto.iwtrecibos.core.mapper.PessoaMapper;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.PessoaService;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.UsuarioService;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.PessoaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PessoaUpdateUsecase {

    private final PessoaService pessoaService;
    private final UsuarioService usuarioService;
    private final PessoaMapper pessoaMapper;

    public PessoaDto atualizarPessoa(PessoaDto antigaPessoa, String uuuidUsuario){
        var usuario = this.usuarioService.recuperarUsuarioPorUuid(uuuidUsuario);
        Pessoa pessoa = this.pessoaService.buscaPessoaPorId(antigaPessoa.getId(), usuario);
        var pessoaParaAtualizar = this.pessoaMapper.toPessoaEntity(antigaPessoa);
        pessoaParaAtualizar.setDataCriacao(pessoa.getDataCriacao());
        pessoaParaAtualizar.setUsuario(usuario);
        log.info("Convertendo para entidade de pessoa, {}", pessoaParaAtualizar);
        pessoa = this.pessoaService.gravarNovaPessoa(pessoaParaAtualizar);
        var pessoaDto = this.pessoaMapper.toPessoaDto(pessoa);
        log.info("Convertendo para o dto de usuário, {}", pessoaDto);
        return pessoaDto;
    }

}
