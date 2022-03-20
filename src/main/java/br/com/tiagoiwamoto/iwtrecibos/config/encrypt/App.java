package br.com.tiagoiwamoto.iwtrecibos.config.encrypt;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 03/03/2022 | 06:53
 */

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class App {

    public static void main(String[] args) {
        var result = AesEncrypt.encrypt("outlook30dez84");
        System.out.println(result);
        var resultDecrypted = AesEncrypt.decrypt(result);
        System.out.println(resultDecrypted);
    }

}
