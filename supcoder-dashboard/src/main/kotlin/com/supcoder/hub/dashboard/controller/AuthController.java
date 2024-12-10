package com.supcoder.hub.dashboard.controller;

import com.supcoder.hub.core.util.IpUtil;
import com.supcoder.hub.core.util.JWTUtil;
import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.core.util.ResultUtil;
import com.supcoder.hub.dashboard.model.vo.FakeCaptcha;
import com.supcoder.hub.dashboard.model.vo.LoginParams;
import com.supcoder.hub.dashboard.model.vo.LoginResult;
import com.supcoder.hub.db.domain.User;
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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest user) {
        // user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        // userService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        var currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResponseEntity.ok("Logged out successfully");
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
        var currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            return ResponseEntity.status(401).body("用户帐号或密码不正确");
        } catch (LockedAccountException lae) {
            return ResponseEntity.status(401).body("用户帐号已锁定不可用");
        } catch (AuthenticationException ae) {
            return ResponseEntity.status(401).body("认证失败");
        }
        currentUser = SecurityUtils.getSubject();
        User user = (User) currentUser.getPrincipal();
        user.setLastLoginIp(IpUtil.getRemoteIp(request));
        user.setLastLoginTime(LocalDateTime.now());
        userService.updateById(user);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("nickName", user.getUsername());
        userInfo.put("avatar", user.getAvatar());

        Map<String, Object> result = new HashMap<>();
        result.put("token", currentUser.getSession().getId());
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
        String token = JWTUtil.INSTANCE.generateToken("supcoder");
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

