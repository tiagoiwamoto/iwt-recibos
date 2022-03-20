package br.com.tiagoiwamoto.iwtrecibos.entrypoint;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:49
 */

import br.com.tiagoiwamoto.iwtrecibos.config.rest.ApiResponseDto;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.pessoa.PessoaCreateUsecase;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.pessoa.PessoaRecoveryUsecase;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.pessoa.PessoaUpdateUsecase;
import br.com.tiagoiwamoto.iwtrecibos.core.usecase.usuario.UsuarioCreateUsecase;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.PessoaDto;
import br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(path = "/pessoas")
@RequiredArgsConstructor
@Slf4j
public class PessoaResource {

    private final PessoaCreateUsecase pessoaCreateUsecase;
    private final PessoaRecoveryUsecase pessoaRecoveryUsecase;
    private final PessoaUpdateUsecase pessoaUpdateUsecase;

    @PostMapping
    public ResponseEntity<ApiResponseDto<PessoaDto>> create(@RequestBody PessoaDto pessoaDto, @RequestHeader(name = "x-user-id") String uuid) {
        log.info("iniciando chamada no recurso POST /pessoas");
        var pessoa = this.pessoaCreateUsecase.criarNovaPessoa(pessoaDto, uuid);
        var response = ResponseEntity.created(URI.create("")).body(ApiResponseDto.of(HttpStatus.CREATED.name(), pessoa, ""));
        log.info("criação de uma nova pessoa concluída com sucesso, {}", response);
        return response;
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto<PessoaDto>> update(@RequestBody PessoaDto pessoaDto, @RequestHeader(name = "x-user-id") String uuid) {
        log.info("iniciando chamada no recurso PUT /pessoas");
        var pessoa = this.pessoaUpdateUsecase.atualizarPessoa(pessoaDto, uuid);
        var response = ResponseEntity.created(URI.create("")).body(ApiResponseDto.of(HttpStatus.OK.name(), pessoa, ""));
        log.info("atualização da pessoa concluída com sucesso, {}", response);
        return response;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<PessoaDto>>> recoveryByUsuario(@RequestHeader(name = "x-user-id") String uuid) {
        log.info("iniciando chamada no recurso GET /pessoas");
        var pessoas = this.pessoaRecoveryUsecase.recuperarPessoasDeUmUsuario(uuid);
        var response = ResponseEntity.ok().body(ApiResponseDto.of(HttpStatus.OK.name(), pessoas, ""));
        log.info("recuperação de pessoas concluída com sucesso, {}", response);
        return response;
    }
}
