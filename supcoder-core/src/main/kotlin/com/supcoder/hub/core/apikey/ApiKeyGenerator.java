package com.supcoder.hub.core.apikey;


import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * ApiKeyGenerator
 *
 * @author lee
 * @date 2024/12/12
 */
public class ApiKeyGenerator {

    public static String generateApiKey() {
        UUID uuid = UUID.randomUUID();
        byte[] uuidBytes = uuid.toString().getBytes(StandardCharsets.UTF_8);
        return Base64.encodeBase64URLSafeString(uuidBytes);
    }

    public static String generateSecretKey(String apiKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest((apiKey + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64URLSafeString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

}
