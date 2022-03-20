package br.com.tiagoiwamoto.iwtrecibos.core.repository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:48
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUuid(String uuid);
    Optional<Usuario> findByUsername(String username);

}
