package com.supcoder.system.domain.model;

import java.util.Date;

/**
 * RotatedApiKey
 *
 * @author lee
 * @date 2024/12/12
 */
public class RotatedApiKey {
    private String id;
    private String newAccessKey;
    private String newSecretKey;
    private Date rotatedAt;
}
