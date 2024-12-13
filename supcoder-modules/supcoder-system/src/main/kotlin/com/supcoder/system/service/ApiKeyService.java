//package com.supcoder.system.service;
//
//
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
///**
// * ApiKeyService
// *
// * @author lee
// * @date 2024/12/12
// */
//@Service
//public class ApiKeyService {
//
//    @Resource
//    private ApiKeysMapper apiKeysMapper;
//
//    @Resource
//    private ApiCallLogsMapper apiCallLogsMapper;
//
//
//    public int createApiKey(String apiKey, String secretKey, String userName, String permissions, String service) {
//        ApiKeys apiKeys = new ApiKeys();
//        apiKeys.setAccessKey(apiKey);
//        apiKeys.setSecretKey(secretKey);
//        apiKeys.setUsername(userName);
//        apiKeys.setPermissions(permissions);
//        apiKeys.setService(service);
//        return apiKeysMapper.insert(apiKeys);
//    }
//
//
//    public List<ApiKeys> getApiKeyList(String userName) {
//        ApiKeysExample apiKeysExample = new ApiKeysExample();
//        apiKeysExample.createCriteria().andUsernameEqualTo(userName);
//        return apiKeysMapper.selectByExample(apiKeysExample);
//    }
//
//
////    public int updateApiKeyStatus(String apiKeyId, boolean status) {
////        return apiKeysMapper.updateByPrimaryKeySelective(ApiKeys.builder().apiKeyId(apiKeyId).status(status).build());
////    }
//
//
//    public boolean deleteApiKey(Integer apiKeyId) {
//        return apiKeysMapper.deleteByPrimaryKey(apiKeyId) > 0;
//    }
//
//
//    public List<ApiCallLogs> getApiCallLogs(Integer apiKeyId, LocalDateTime startDate, LocalDateTime endDate) {
//        if (startDate != null && endDate != null) {
//            return apiCallLogsMapper.selectByExample(ApiCallLogsExample.newAndCreateCriteria()
//                    .andKeyIdEqualTo(apiKeyId)
//                    .andRequestDateBetween(startDate, endDate)
//                            .example()
//                    );
//        }
//        return apiCallLogsMapper.selectByExample(ApiCallLogsExample.newAndCreateCriteria()
//                .andKeyIdEqualTo(apiKeyId)
//                .example()
//        );
//    }
//}
