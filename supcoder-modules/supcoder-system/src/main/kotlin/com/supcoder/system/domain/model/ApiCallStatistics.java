package com.supcoder.system.domain.model;

import java.util.Date;

/**
 * ApiCallStatistics
 *
 * @author lee
 * @date 2024/12/12
 */
public class ApiCallStatistics {
    private String userId;
    private String service;
    private Date startDate;
    private Date endDate;
    private long totalCalls;
    private long successfulCalls;
    private long failedCalls;

}
