package com.supcoder.hub.dashboard.auth;


import com.supcoder.hub.core.exception.TipException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * JwtUtil
 *
 * @author lee
 * @date 2024/12/10
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access.expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String generateAccessToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims extractClaims(String token) throws TipException {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // 处理过期的令牌
            throw new TipException(HttpStatus.UNAUTHORIZED.value(), "Token has expired");
        } catch (SignatureException e) {
            // 处理签名不匹配的情况
            throw new TipException(HttpStatus.UNAUTHORIZED.value(), "Invalid token signature");
        } catch (MalformedJwtException e) {
            // 处理格式错误的令牌
            throw new TipException(HttpStatus.UNAUTHORIZED.value(), "Invalid token format");
        }
    }

    public String extractUsername(String token) {
        try {
            return extractClaims(token).getSubject();
        } catch (Exception exception) {
            return "";
        }
    }

    public boolean isTokenExpired(String token) {
        if (token == null) {
            return true;
        }
        try {
            return extractClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }


    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(extractUsername(token)) &&
                    !isTokenExpired(token)) &&
                    !isTokenInBlacklist(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isTokenInBlacklist(String token) {
        return redisTemplate.hasKey("blacklisted:" + token);
    }

    public void addToBlacklist(String token) {
        redisTemplate.opsForValue().set("blacklisted:" + token, "true", refreshExpiration, TimeUnit.SECONDS);
    }

}

