package com.supcoder.common.core.apikey;


import com.supcoder.hub.core.model.ApiKeyModel;

import java.util.UUID;

/**
 * ApiKeyGenerator
 *
 * @author lee
 * @date 2024/12/12
 */
public class ApiKeyGenerator {

    public static ApiKeyModel generateApiKey() {
        UUID key = UUID.randomUUID();
        UUID secret = UUID.randomUUID();
        ApiKeyModel apiKey = new ApiKeyModel();
        apiKey.setApiKey(key.toString().replace("-", ""));
        apiKey.setApiKey(secret.toString().replace("-", ""));
        return apiKey;
    }



}
