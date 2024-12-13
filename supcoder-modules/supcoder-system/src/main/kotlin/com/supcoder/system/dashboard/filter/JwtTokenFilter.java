
package com.supcoder.system.dashboard.filter;

import com.supcoder.system.dashboard.auth.JwtToken;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * JwtTokenFilter
 *
 * @author lee
 * @date 2024/12/10
 */
@Slf4j
public class JwtTokenFilter extends BasicHttpAuthenticationFilter {


    private static final String HEADER_TOKEN = "Authorization";

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorization = httpRequest.getHeader(HEADER_TOKEN);
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authorization = httpRequest.getHeader(HEADER_TOKEN);
        JwtToken jwtToken = new JwtToken(authorization);
        try {
            getSubject(request, response).login(jwtToken);
        } catch (AuthenticationException e) {
            responseError(response, HttpStatus.UNAUTHORIZED.value(),e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                log.error("执行response.getWriter()方法异常");
            }
        }
        this.sendChallenge(request, response);
        return false;
    }


    /**
     * 向客户端响应错误信息
     *
     * 当请求处理过程中发生错误时，调用此方法将错误信息编码并重定向到错误页面
     *
     * @param resp ServletResponse对象，用于响应客户端请求
     * @param code 错误代码，用于标识不同的错误类型
     * @param errorMessage 错误消息，描述错误的详细信息
     * @throws IOException 当响应过程中发生I/O错误时抛出此异常
     */
    private void responseError(ServletResponse resp, int code, String errorMessage) throws IOException{
        try {
            // 将ServletResponse转换为HttpServletResponse，以便支持HTTP特定的操作
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            // 对错误消息进行URL编码，以确保消息在URL中的安全传输
            errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
            // 重定向到错误页面，携带错误代码和编码后的错误消息作为URL参数
            httpServletResponse.sendRedirect("/filterError/"+code+"/"+errorMessage);
        } catch (Exception e) {
            // 记录错误日志，便于问题追踪和定位
            log.error(e.getMessage());
        }
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
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