package br.com.tiagoiwamoto.iwtrecibos.core.repository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:48
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Pessoa;
import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findAllByUsuario(Usuario usuario);
    Optional<Pessoa> findByCpfCnpjAndUsuario(String cpfCnpj, Usuario usuario);
    List<Pessoa> findByNomeLikeAndUsuario(String nome, Usuario usuario);
    List<Pessoa> findByEnderecoLikeAndUsuario(String endereco, Usuario usuario);
    List<Pessoa> findByCepAndUsuario(String cep, Usuario usuario);

}
