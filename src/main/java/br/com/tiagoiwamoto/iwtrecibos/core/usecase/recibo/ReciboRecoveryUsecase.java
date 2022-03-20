package br.com.tiagoiwamoto.iwtrecibos.core.usecase.recibo;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 04/02/2022 | 07:17
 */

import br.com.tiagoiwamoto.iwtrecibos.core.mapper.ReciboMapper;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.ReciboService;
import br.com.tiagoiwamoto.iwtrecibos.core.service.database.UsuarioService;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.ReciboDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ReciboRecoveryUsecase {

    private final ReciboService reciboService;
    private final UsuarioService usuarioService;
    private final ReciboMapper reciboMapper;

    public List<ReciboDto> recuperarRecibosdeUmUsuario(String uuuidUsuario){
        var usuario = this.usuarioService.recuperarUsuarioPorUuid(uuuidUsuario);
        var recibos = this.reciboService.recuperarRecibosDeUmUsuario(usuario);
        log.info("recibos recuperados com sucesso, {}", recibos.size());
        var recibosDto = recibos.stream().map(this.reciboMapper::toReciboDto).collect(Collectors.toList());
        log.info("recibos convertidos com sucesso, {}", recibosDto.size());
        return recibosDto;
    }
}
