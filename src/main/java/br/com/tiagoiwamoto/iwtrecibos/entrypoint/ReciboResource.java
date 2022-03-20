package br.com.tiagoiwamoto.iwtrecibos.entrypoint;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:49
 */

import br.com.tiagoiwamoto.iwtrecibos.config.rest.ApiResponseDto;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.recibo.ReciboCreateUsecase;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.recibo.ReciboRecoveryUsecase;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.usuario.UsuarioCreateUsecase;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.ReciboDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/recibos")
@RequiredArgsConstructor
@Slf4j
public class ReciboResource {

    private final ReciboCreateUsecase reciboCreateUsecase;
    private final ReciboRecoveryUsecase reciboRecoveryUsecase;

    @PostMapping
    public ResponseEntity<ApiResponseDto<ReciboDto>> create(@RequestBody ReciboDto reciboDto, @RequestHeader(name = "x-user-id") String uuid){
        log.info("iniciando chamada no recurso POST /recibos");
        var recibo = this.reciboCreateUsecase.criarNovoRecibo(reciboDto, uuid);
        var response = ResponseEntity.created(URI.create("")).body(ApiResponseDto.of(HttpStatus.CREATED.name(), recibo, ""));
        log.info("criação de um novo recibo foi concluída com sucesso, {}", response);
        return response;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ReciboDto>>> recovery(@RequestHeader(name = "x-user-id") String uuid){
        log.info("iniciando chamada no recurso GET /recibos");
        var recibos = this.reciboRecoveryUsecase.recuperarRecibosdeUmUsuario(uuid);
        var response = ResponseEntity.created(URI.create("")).body(ApiResponseDto.of(HttpStatus.CREATED.name(), recibos, ""));
        log.info("recibos recuperados com sucesso, {}", response);
        return response;
    }

}
