package br.com.tiagoiwamoto.iwtrecibos.entrypoint.dto;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:34
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaDto {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpfCnpj;
    private String cep;
    private String endereco;
    private String cidade;
    private String uf;
    private LocalDateTime dataCriacao;

}
