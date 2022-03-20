package br.com.tiagoiwamoto.iwtrecibos.core.util;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 03/02/2022 | 06:51
 */

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encript {

    public static String md5Convert(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes(StandardCharsets.UTF_8));
        var digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

}
