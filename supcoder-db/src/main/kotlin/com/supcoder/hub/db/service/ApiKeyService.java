package com.supcoder.hub.db.service;

import com.supcoder.hub.db.dao.ApiCallLogsMapper;
import com.supcoder.hub.db.dao.ApiKeysMapper;
import com.supcoder.hub.db.domain.ApiCallLogs;
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
}
