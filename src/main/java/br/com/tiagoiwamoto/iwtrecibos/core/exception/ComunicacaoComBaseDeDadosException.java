package br.com.tiagoiwamoto.iwtrecibos.core.exception;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 03/02/2022 | 07:08
 */

public class ComunicacaoComBaseDeDadosException extends RuntimeException{

    public ComunicacaoComBaseDeDadosException() {
        super("Não foi possível se comunicar com nossa base de dados no momento, tente novamente mais tarde.");
    }
}
