package br.com.tiagoiwamoto.iwtrecibos.core.usecase.recibo;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 04/02/2022 | 07:03
 */

import br.com.tiagoiwamoto.iwtrecibos.core.mapper.ReciboMapper;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.ReciboService;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.UsuarioService;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.ReciboDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ReciboCreateUsecase {

    private final ReciboService reciboService;
    private final UsuarioService usuarioService;
    private final ReciboMapper reciboMapper;

    public ReciboDto criarNovoRecibo(ReciboDto reciboDto, String uuuidUsuario){
        var usuario = this.usuarioService.recuperarUsuarioPorUuid(uuuidUsuario);
        var recibo = this.reciboMapper.toReciboEntity(reciboDto);
        log.info("Convertendo para entidade de recibo, {}", recibo);
        recibo.comDataCriacao();
        recibo.setUsuario(usuario);
        recibo = this.reciboService.gravarNovoRecibo(recibo);
        reciboDto = this.reciboMapper.toReciboDto(recibo);
        log.info("Convertendo para o dto de recibo, {}", reciboDto);
        return reciboDto;
    }

}
