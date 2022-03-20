package br.com.tiagoiwamoto.iwtrecibos.core.entity;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 01/02/2022 | 06:26
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "iwt_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uuid;
    private String username;
    private String password;
    private LocalDateTime dataCriacao;

    public void comDataCriacao(){
        this.dataCriacao = LocalDateTime.now();
    }
    public void gerarUuid(){
        var camposParaGerarUuid = String.format("%s%s%s", this.username, this.password, this.dataCriacao);
        this.uuid = UUID.nameUUIDFromBytes(camposParaGerarUuid.getBytes(StandardCharsets.UTF_8)).toString();
    }

}
