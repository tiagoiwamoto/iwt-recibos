package br.com.tiagoiwamoto.iwtrecibos.core.service.database;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 04/02/2022 | 06:56
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.Recibo;
import br.com.tiagoiwamoto.iwtrecibos.core.entity.Usuario;
import br.com.tiagoiwamoto.iwtrecibos.core.exception.ComunicacaoComBaseDeDadosException;
import br.com.tiagoiwamoto.iwtrecibos.core.repository.ReciboRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReciboService {

    private final ReciboRepository reciboRepository;

    public Recibo gravarNovoRecibo(Recibo recibo){
        try{
            log.info("Iniciando gravação de um novo recibo, {}", recibo);
            var reciboSalvo = this.reciboRepository.save(recibo);
            log.info("recibo gravado no banco de dados com sucesso, {}", reciboSalvo);
            return reciboSalvo;
        }catch (Exception e){
            log.error("Falha ao gravar no banco de dados, {}", recibo);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

    public List<Recibo> recuperarRecibosDeUmUsuario(Usuario usuario){
        try{
            log.info("recuperando recibos de um usuário");
            var recibos = this.reciboRepository.findAllByUsuario(usuario);
            log.info("recibos do usuário {} recuperados com sucesso, total de recibos {}", usuario.getUsername(), recibos.size());
            return recibos;
        }catch (Exception e){
            log.error("Falha ao gravar no banco de dados, {}", usuario);
            throw new ComunicacaoComBaseDeDadosException();
        }
    }

}
