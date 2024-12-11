package com.supcoder.hub.dashboard.model.dto.request;

/**
 * LogoutRequest
 *
 * @author lee
 * @date 2024/12/11
 */
public class LogoutRequest {
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

