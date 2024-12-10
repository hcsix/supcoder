package com.supcoder.hub.dashboard.filter;

import com.supcoder.hub.dashboard.auth.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * JwtTokenFilter
 *
 * @author lee
 * @date 2024/12/10
 */
public class JwtTokenFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorization = httpRequest.getHeader("Authorization");
        return authorization != null && authorization.startsWith("Bearer ");
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorization = httpRequest.getHeader("Authorization");
        String token = authorization.substring(7); // 去掉 "Bearer " 前缀
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
            } catch (Exception e) {
                response401(request, response);
            }
        }
        return true;
    }

    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        response401(request, response);
        return false;
    }

    private void response401(ServletRequest req, ServletResponse resp) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

}