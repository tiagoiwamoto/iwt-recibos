package br.com.tiagoiwamoto.iwtrecibos.entrypoint;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/02/2022 | 19:51
 */

import br.com.tiagoiwamoto.iwtrecibos.config.rest.ApiResponseDto;
import br.com.tiagoiwamoto.iwtrecibos.core.service.rest.ViacepGateway;
import br.com.tiagoiwamoto.iwtrecibos.core.service.rest.dto.ViacepDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cep")
@RequiredArgsConstructor
@Slf4j
public class ViacepResource {

    private final ViacepGateway viacepGateway;

    @GetMapping(path = "/{cep}")
    public ResponseEntity<ApiResponseDto<ViacepDto>> recoveryAddressByCep(@PathVariable(name = "cep") String cep) {
        log.info("iniciando chamada no recurso GET /cep");
        var cepResponse = this.viacepGateway.call(cep);
        var response = ResponseEntity.ok().body(ApiResponseDto.of(HttpStatus.OK.name(), cepResponse.getBody(), ""));
        log.info("endereço recuperado com sucesso, {}", response.getBody());
        return response;
    }
}
