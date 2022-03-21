package br.com.tiagoiwamoto.iwtrecibos.entrypoint;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 02/03/2022 | 07:39
 */

import br.com.tiagoiwamoto.iwtrecibos.config.rest.ApiResponseDto;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.recibo.EnviarReciboPorEmailUsecase;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.ReciboDto;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping(path = "/email")
@RequiredArgsConstructor
@Slf4j
public class EmailResource {

    private final EnviarReciboPorEmailUsecase enviarReciboPorEmailUsecase;

    @PostMapping
    public ResponseEntity<ApiResponseDto<Object>> enviarEmail(@RequestBody ReciboDto reciboDto) throws MessagingException, DocumentException, IOException {
        log.info("iniciando chamada no recurso POST /email");
        this.enviarReciboPorEmailUsecase.enviaReciboPorEmail(reciboDto);
        var response = ResponseEntity.accepted().body(ApiResponseDto.of(HttpStatus.ACCEPTED.name(), null, "Email enviado com sucesso"));
        log.info("criação de uma nova pessoa concluída com sucesso, {}", response);
        return response;
    }

}
