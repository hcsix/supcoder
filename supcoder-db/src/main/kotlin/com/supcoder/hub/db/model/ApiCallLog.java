package com.supcoder.hub.db.model;

import java.util.Date;

/**
 * ApiCallLog
 *
 * @author lee
 * @date 2024/12/12
 */
public class ApiCallLog {
    private String apiKeyId;
    private Date callTime;
    private String service;
    private String status; // "success" or "failure"

}
