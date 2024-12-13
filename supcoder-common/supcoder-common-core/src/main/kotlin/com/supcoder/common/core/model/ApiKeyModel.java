package com.supcoder.hub.core.model;

/**
 * ApiKey
 *
 * @author lee
 * @date 2024/12/12
 */
public class ApiKeyModel {
    private String apiKey;
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
