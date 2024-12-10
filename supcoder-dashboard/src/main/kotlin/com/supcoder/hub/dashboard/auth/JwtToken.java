package com.supcoder.hub.dashboard.auth;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 *
 * @author lee
 * @date 2024/12/10
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

