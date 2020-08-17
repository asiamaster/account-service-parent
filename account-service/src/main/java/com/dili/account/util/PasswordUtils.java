package com.dili.account.util;

import java.nio.charset.StandardCharsets;

/**
 * 密码加密散列工具类
 *
 * @author: brenthuang
 * @date: 2017/12/28
 */
public class PasswordUtils {
    public static String generateSecretKey() {
        try {
            return AesCipher.generateSecretKey();
        } catch (Exception ex) {
            throw new RuntimeException("Generate password secret key error", ex);
        }
    }

    public static String encrypt(String password, String secretKey) {
        try {
            byte[] data = password.getBytes(StandardCharsets.UTF_8);
            return HexUtils.encodeHexStr(ShaCipher.encrypt(AesCipher.encrypt(data, secretKey)));
        } catch (Exception ex) {
            throw new RuntimeException("Encrypt password error", ex);
        }
    }

}
