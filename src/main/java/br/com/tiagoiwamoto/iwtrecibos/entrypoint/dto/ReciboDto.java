package br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:07
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.enums.TipoReciboEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReciboDto {

    private Long id;
    private TipoReciboEnum tipo;
    private BigDecimal valor;
    private String valorPorExtenso;
    private PessoaDto pagador;
    private String localRecebido;
    private String referente;
    private String observacao;
    private LocalDate dataDeEmissao;
    private String dataDeEmissaoPorExtenso;
    private PessoaDto assinante;
    private LocalDateTime dataCriacao;

}
