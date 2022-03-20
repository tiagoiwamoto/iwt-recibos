package br.com.tiagoiwamoto.iwtrecibos.core.exception;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 03/02/2022 | 07:08
 */

public class PessoaNaoEncontradaException extends RuntimeException{

    public PessoaNaoEncontradaException() {
        super("Não localizamos esta pessoa em nossa base de dados.");
    }
}
