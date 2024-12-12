package com.supcoder.hub.db.service;

import com.supcoder.hub.db.dao.ApiCallLogsMapper;
import com.supcoder.hub.db.dao.ApiKeysMapper;
import com.supcoder.hub.db.domain.ApiCallLogs;
import com.supcoder.hub.db.domain.ApiKeys;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ApiKeyService
 *
 * @author lee
 * @date 2024/12/12
 */
@Service
public class ApiKeyService {

    @Resource
    private ApiKeysMapper apiKeysMapper;

    @Resource
    private ApiCallLogsMapper apiCallLogsMapper;


    public int createApiKey(String apiKey, String secretKey, String userName, String permissions, String service) {
        ApiKeys apiKeys = new ApiKeys();
        apiKeys.setAccessKey(apiKey);
        apiKeys.setSecretKey(secretKey);
        apiKeys.setUsername(userName);
        apiKeys.setPermissions(permissions);
        apiKeys.setService(service);
        return apiKeysMapper.insert(apiKeys);
    }


//    public int updateApiKeyStatus(String apiKeyId, boolean status) {
//        return apiKeysMapper.updateByPrimaryKeySelective(ApiKeys.builder().apiKeyId(apiKeyId).status(status).build());
//    }
}
