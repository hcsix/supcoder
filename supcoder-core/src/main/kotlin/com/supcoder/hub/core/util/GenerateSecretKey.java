package com.supcoder.hub.core.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class GenerateSecretKey {

    public static void main(String[] args) {
        // 生成一个符合 HS512 要求的密钥
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // 将密钥转换为字节数组
        byte[] keyBytes = secretKey.getEncoded();

        // 将字节数组转换为 Base64 编码的字符串
        String base64EncodedSecretKey = Base64.getEncoder().encodeToString(keyBytes);

        // 打印生成的密钥字符串
        System.out.println("Generated Secret Key: " + base64EncodedSecretKey);
    }
}
