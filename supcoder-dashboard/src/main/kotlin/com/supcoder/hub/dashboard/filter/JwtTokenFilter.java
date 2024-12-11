
package com.supcoder.hub.dashboard.filter;

import com.supcoder.hub.core.exception.TipException;
import com.supcoder.hub.core.validator.Order;
import com.supcoder.hub.dashboard.auth.JwtToken;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * JwtTokenFilter
 *
 * @author lee
 * @date 2024/12/10
 */
@Slf4j
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
            } catch (UnknownAccountException e) {
                log.error("UnknownAccountException caught: {}", e.getMessage(), e);
                response401(request, response, e.getMessage());
                return false;
            } catch (AccountException e) {
                log.error("AccountException caught: {}", e.getMessage(), e);
                response401(request, response, e.getMessage());
                return false;
            } catch (IncorrectCredentialsException e) {
                log.error("IncorrectCredentialsException caught: {}", e.getMessage(), e);
                response401(request, response, e.getMessage());
                return false;
            } catch (TipException e) {
                log.error("TipException caught: {}", e.getMessage(), e);
                throw e;
            } catch (Exception e) {
                log.error("General exception caught: {}", e.getMessage(), e);
                response401(request, response, e.getMessage());
                return false;
            }
        } else {
            response401(request, response, "Unauthorized");
        }
        return false;
    }

    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        response401(request, response);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String authorization = httpRequest.getHeader("Authorization");
        String token = authorization.substring(7); // 去掉 "Bearer " 前缀
        if (token != null) {
            try {
                getSubject(request, response).login(new JwtToken(token));
                return true;
            } catch (AuthenticationException e) {
                // 重新抛出异常，确保全局异常处理器能够捕获
                throw e;
            }
        }
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        return false;
    }


    private void response401(ServletRequest req, ServletResponse resp) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
    }


    private void response401(ServletRequest req, ServletResponse resp, String errorMessage) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        try {
            String jsonResponse = "{\"error\": \"" + errorMessage + "\"}";
            httpServletResponse.getWriter().write(jsonResponse);
        } catch (IOException e) {
            log.error("Failed to write response: {}", e.getMessage(), e);
        }
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getMethod().equals("OPTIONS")) {
            httpResponse.setStatus(HttpStatus.OK.value());
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
            return false;
        }
        return super.preHandle(request, response);
    }
}