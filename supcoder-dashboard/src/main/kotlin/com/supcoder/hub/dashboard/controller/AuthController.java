package com.supcoder.hub.dashboard.controller;

import com.supcoder.hub.core.util.IpUtil;
import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.core.util.ResultUtil;
import com.supcoder.hub.dashboard.auth.JwtUtil;
import com.supcoder.hub.dashboard.model.vo.*;
import com.supcoder.hub.db.domain.User;
import com.supcoder.hub.db.service.AuthService;
import com.supcoder.hub.db.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
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

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        var currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResponseEntity.ok("Logged out successfully");
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();
        if (jwtUtil.validateToken(refreshToken, jwtUtil.extractUsername(refreshToken))) {
            String username = jwtUtil.extractUsername(refreshToken);
            String accessToken = jwtUtil.generateAccessToken(username);
            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
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
        if (!userService.checkUsername(username)){
            return ResponseEntity.status(401).body("用户帐号或密码不正确");
        }
        User user = authService.authenticate(username, password);
        if (user == null){
            return ResponseEntity.status(401).body("认证失败");
        }
        user.setLastLoginIp(IpUtil.getRemoteIp(request));
        user.setLastLoginTime(LocalDateTime.now());
        userService.updateById(user);

        // 生成 JWT
        String accessToken = jwtUtil.generateAccessToken(user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("nickName", user.getUsername());
        userInfo.put("avatar", user.getAvatar());

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("refreshToken", refreshToken);
        result.put("adminInfo", userInfo);
        return ResponseEntity.ok(ResultUtil.success(result));
    }

    /**
     * 模拟登录接口，用于测试
     *
     * @return 登录成功返回token，否则返回错误信息
     */
    @GetMapping("/mock-login")
    public ResponseEntity<JsonResult<String>> mockLoginRequest() {
        String token = jwtUtil.generateAccessToken("supcoder");
        JsonResult<String> response = ResultUtil.success(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/captcha")
    public ResponseEntity<FakeCaptcha> getFakeCaptcha(@RequestParam String phone) {
        FakeCaptcha fakeCaptcha = new FakeCaptcha(12345, "success");
        return ResponseEntity.ok(fakeCaptcha);
    }

    @PostMapping("/account")
    public ResponseEntity<JsonResult<String>> login(@RequestBody LoginParams loginParams) {
        return ResponseEntity.ok(ResultUtil.success("12212"));
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


}

