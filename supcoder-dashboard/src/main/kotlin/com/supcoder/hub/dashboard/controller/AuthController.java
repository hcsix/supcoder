package com.supcoder.hub.dashboard.controller;

import com.supcoder.hub.core.util.IpUtil;
import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.core.util.ResultUtil;
import com.supcoder.hub.dashboard.auth.JwtUtil;
import com.supcoder.hub.dashboard.model.dto.request.LoginRequest;
import com.supcoder.hub.dashboard.model.dto.request.LogoutRequest;
import com.supcoder.hub.dashboard.model.dto.request.RefreshTokenRequest;
import com.supcoder.hub.dashboard.model.vo.*;
import com.supcoder.hub.db.domain.User;
import com.supcoder.hub.db.service.AuthService;
import com.supcoder.hub.db.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthController
 *
 * @author lee
 * @date 2024/12/6
 */
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest user) {
        if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(400).body("Username and password cannot be empty");
        }
        // 检查用户名是否已存在
        if (userService.checkUsername(user.getUsername())) {
            return ResponseEntity.status(400).body("Username already exists");
        }
        // 创建新用户
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword()); // 注意：这里应该对密码进行加密
        newUser.setLastLoginIp(IpUtil.getRemoteIp(null)); // 可以根据需要设置默认值
        newUser.setLastLoginTime(LocalDateTime.now()); // 可以根据需要设置默认值
        userService.add(newUser);
        // 生成 JWT
        String accessToken = jwtUtil.generateAccessToken(user.getUsername());
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("nickName", newUser.getUsername());
        userInfo.put("avatar", newUser.getAvatar());
        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("userInfo", userInfo);
        return ResponseEntity.ok(ResultUtil.success(result));
    }



    /**
     * 登录接口
     *
     * @param loginRequest 登录请求参数
     * @return 登录成功返回token，否则返回错误信息
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.status(401).body("Username or password cannot be empty");
        }
        if (!userService.checkUsername(username)) {
            return ResponseEntity.status(401).body("用户帐号或密码不正确");
        }
        User user = authService.authenticate(username, password);
        if (user == null) {
            return ResponseEntity.status(401).body("认证失败");
        }
        user.setLastLoginIp(IpUtil.getRemoteIp(request));
        user.setLastLoginTime(LocalDateTime.now());
        userService.updateById(user);
        // 生成 JWT
        String accessToken = jwtUtil.generateAccessToken(user.getUsername());
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("nickName", user.getUsername());
        userInfo.put("avatar", user.getAvatar());

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("userInfo", userInfo);
        return ResponseEntity.ok(ResultUtil.success(result));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest request) {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            if (currentUser != null && currentUser.isAuthenticated()) {
                currentUser.logout();
                logger.info("User logged out successfully.");

                // 获取当前请求中的 Refresh Token
                String refreshToken = request.getRefreshToken();
                if (refreshToken != null) {
                    // 将 Refresh Token 添加到黑名单
                    jwtUtil.addToBlacklist(refreshToken);
                }
            } else {
                logger.warn("Attempted to log out an unauthenticated user.");
            }
            return ResponseEntity.ok("Logged out successfully");
        } catch (Exception e) {
            logger.error("Error during logout", e);
            return ResponseEntity.status(500).body("An error occurred during logout");
        }
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        if (jwtUtil.validateToken(refreshToken)) {
            String username = jwtUtil.extractUsername(refreshToken);
            String accessToken = jwtUtil.generateAccessToken(username);
            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
    }


    @PostMapping("/account")
    public ResponseEntity<JsonResult<String>> login(@RequestBody LoginParams loginParams) {
        return ResponseEntity.ok(ResultUtil.success("12212"));
    }


}

