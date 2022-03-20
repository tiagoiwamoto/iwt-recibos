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
public class PessoaCreateUsecase {

    private final PessoaService pessoaService;
    private final UsuarioService usuarioService;
    private final PessoaMapper pessoaMapper;

    public PessoaDto criarNovaPessoa(PessoaDto novaPessoa, String uuuidUsuario){
        var usuario = this.usuarioService.recuperarUsuarioPorUuid(uuuidUsuario);
        Pessoa pessoa = this.pessoaMapper.toPessoaEntity(novaPessoa);
        log.info("Convertendo para entidade de pessoa, {}", pessoa);
        pessoa.comDataCriacao();
        pessoa.setUsuario(usuario);
        pessoa = this.pessoaService.gravarNovaPessoa(pessoa);
        var pessoaDto = this.pessoaMapper.toPessoaDto(pessoa);
        log.info("Convertendo para o dto de usuário, {}", pessoaDto);
        return pessoaDto;
    }

}
