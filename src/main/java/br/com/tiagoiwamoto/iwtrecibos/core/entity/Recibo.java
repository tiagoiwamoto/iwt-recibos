package br.com.tiagoiwamoto.iwtrecibos.core.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:07
 */

import br.com.tiagoiwamoto.iwtrecibos.core.entity.enums.TipoReciboEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "iwt_recibo")
public class Recibo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private TipoReciboEnum tipo;
    private BigDecimal valor;
    private String valorPorExtenso;
    @ManyToOne
    @JoinColumn(name = "tbl_pagador_id")
    private Pessoa pagador;
    private String localRecebido;
    private String referente;
    private String observacao;
    private LocalDate dataDeEmissao;
    private String dataDeEmissaoPorExtenso;
    @ManyToOne
    @JoinColumn(name = "tbl_assinante_id")
    private Pessoa assinante;
    @ManyToOne
    @JoinColumn(name = "tbl_usuario_id")
    private Usuario usuario;
    private LocalDateTime dataCriacao;

    public void comDataCriacao(){
        this.dataCriacao = LocalDateTime.now();
    }

}
