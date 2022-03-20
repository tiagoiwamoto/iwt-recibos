package br.com.tiagoiwamoto.iwtrecibos.core.util;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 03/02/2022 | 07:18
 */

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constantes {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SystemErrors{
        public static final String DATABASE_ACCESS_ERROR = "falha ao se comunicar com a base de dados, {}";
    }

}
