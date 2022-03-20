package br.com.tiagoiwamoto.iwtrecibos.core.repository;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:48
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Recibo;
import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReciboRepository extends JpaRepository<Recibo, Long> {

    List<Recibo> findAllByUsuario(Usuario usuario);

}
