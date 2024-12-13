package com.supcoder.system.domain.model;

import java.util.Date;
import java.util.List;

/**
 * ApiKey
 *
 * @author lee
 * @date 2024/12/12
 */
public class ApiKey {
    private String id;
    private String accessKey;
    private String secretKey;
    private List<String> scope;
    private String service;
    private String status; // "active" or "inactive"
    private Date createdAt;
    private Date lastUsedAt;
}
