package br.com.tiagoiwamoto.iwtrecibos.core.service.database;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 03/02/2022 | 06:52
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Pessoa;
import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import br.com.tiagoiwamoto.iwtrecibos.core.exception.ComunicacaoComBaseDeDadosException;
import br.com.tiagoiwamoto.iwtrecibos.core.exception.PessoaNaoEncontradaException;
import br.com.tiagoiwamoto.iwtrecibos.core.repository.PessoaRepository;
import br.com.tiagoiwamoto.iwtrecibos.core.util.Constantes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa gravarNovaPessoa(Pessoa novaPessoa){
        try{
            log.info("Iniciando gravação de uma nova pessoa, {}", novaPessoa);
            var pessoaSalva = this.pessoaRepository.save(novaPessoa);
            log.info("pessoa gravada no banco de dados com sucesso, {}", pessoaSalva);
            return pessoaSalva;
        }catch (Exception e){
            log.error(Constantes.SystemErrors.DATABASE_ACCESS_ERROR, novaPessoa);
            throw new RuntimeException();
        }
    }

    public List<Pessoa> buscaPessoasPorUsuario(Usuario usuario) {
        try {
            log.info("busca pessoas por usuário, {}",  usuario);
            var pessoas = this.pessoaRepository.findAllByUsuario(usuario);
            log.info("resultado da busca, {}", pessoas);
            return pessoas;
        } catch (Exception e) {
            log.error(Constantes.SystemErrors.DATABASE_ACCESS_ERROR, usuario);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public Pessoa buscaPessoaPorId(Long id, Usuario usuario){
        try{
            log.info("busca pessoa por id iniciado, {} para o usuário, {}", id, usuario);
            var optionalPessoa = this.pessoaRepository.findById(id);
            log.info("resultado da busca, {}", optionalPessoa);
            return optionalPessoa.orElseThrow(PessoaNaoEncontradaException::new);
        }catch (Exception e){
            log.error(Constantes.SystemErrors.DATABASE_ACCESS_ERROR, e);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public Pessoa buscaPessoaPorCpfCnpj(String cpfCnpj, Usuario usuario){
        try{
            log.info("busca pessoa por cpf-cnpj iniciado, {} para o usuário, {}", cpfCnpj.replace("[0-9][6]", "*"), usuario);
            var optionalPessoa = this.pessoaRepository.findByCpfCnpjAndUsuario(cpfCnpj, usuario);
            log.info("resultado da busca, {}", optionalPessoa);
            return optionalPessoa.orElseThrow(PessoaNaoEncontradaException::new);
        }catch (Exception e){
            log.error(Constantes.SystemErrors.DATABASE_ACCESS_ERROR, e);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public List<Pessoa> buscaPessoasPorNome(String nome, Usuario usuario) {
        try {
            log.info("busca pessoas por nome iniciado, {} para o usuário, {}", nome, usuario);
            var pessoas = this.pessoaRepository.findByEnderecoLikeAndUsuario(nome, usuario);
            log.info("resultado da busca, {}", pessoas);
            return pessoas;
        } catch (Exception e) {
            log.error(Constantes.SystemErrors.DATABASE_ACCESS_ERROR, e);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public List<Pessoa> buscaPessoasPorCep(String cep, Usuario usuario){
        try{
            log.info("busca pessoas por cep iniciado, {} para o usuário, {}", cep, usuario);
            var pessoas = this.pessoaRepository.findByCepAndUsuario(cep, usuario);
            log.info("resultado da busca, {}", pessoas);
            return pessoas;
        }catch (Exception e){
            log.error(Constantes.SystemErrors.DATABASE_ACCESS_ERROR, e);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public List<Pessoa> buscaPessoasPorEndereco(String nome, Usuario usuario){
        try{
            log.info("busca pessoas por endereco iniciado, {} para o usuário, {}", nome, usuario);
            var pessoas = this.pessoaRepository.findByEnderecoLikeAndUsuario(nome, usuario);
            log.info("resultado da busca, {}", pessoas);
            return pessoas;
        }catch (Exception e){
            log.error(Constantes.SystemErrors.DATABASE_ACCESS_ERROR, e);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

}
