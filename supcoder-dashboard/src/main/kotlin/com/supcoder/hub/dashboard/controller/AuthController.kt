package com.supcoder.hub.dashboard.controller

import com.supcoder.hub.core.util.IpUtil
import com.supcoder.hub.core.util.JWTUtil
import com.supcoder.hub.core.util.JsonResult
import com.supcoder.hub.core.util.ResultUtil
import com.supcoder.hub.db.domain.User
import com.supcoder.hub.db.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.LockedAccountException
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.tomcat.util.http.ResponseUtil
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * AuthController
 *
 * @author lee
 * @date 2024/12/6
 */
@RestController
@RequestMapping("/api/auth")
@Validated
class AuthController {
    @Autowired
    private lateinit var userService: UserService


    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())
//        userService.save(user)
        return ResponseEntity.ok("User registered successfully")
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        val currentUser = SecurityUtils.getSubject()
        currentUser.logout()
        return ResponseEntity.ok("Logged out successfully")
    }

    /**
     * 登录接口
     * @param loginRequest 登录请求参数
     * @return 登录成功返回token，否则返回错误信息
     */
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest,
        request: HttpServletRequest
    ): ResponseEntity<out Any?>? {
        val username = loginRequest.username
        val password = loginRequest.password
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            return ResponseEntity.status(401).body("Username or password cannot be empty")
        }
        var currentUser = SecurityUtils.getSubject()
        try {
            currentUser.login(UsernamePasswordToken(username, password));
        } catch (uae: UnknownAccountException) {
            return ResponseEntity.status(401).body("用户帐号或密码不正确")
        } catch (lae: LockedAccountException) {
            return ResponseEntity.status(401).body("用户帐号已锁定不可用")
        } catch (ae: AuthenticationException) {
            return ResponseEntity.status(401).body("认证失败")
        }
        currentUser = SecurityUtils.getSubject();
        val user = currentUser.principal as User
        user.lastLoginIp = IpUtil.getRemoteIp(request)
        user.lastLoginTime = LocalDateTime.now();
        userService.updateById(user)

        val userInfo = hashMapOf<String, String>();
        userInfo.put("nickName", user.getUsername());
        userInfo.put("avatar", user.getAvatar());

        val result = hashMapOf<String, Object>();
        result.put("token", currentUser.session.id as Object);
        result.put("adminInfo", userInfo as Object);
        return ResponseEntity.ok(ResultUtil.success(result));
    }


    /**
     * 模拟登录接口，用于测试
     * @param loginRequest 登录请求参数
     * @return 登录成功返回token，否则返回错误信息
     */
    @GetMapping("/mock-login")
    fun mockLoginRequest(): ResponseEntity<JsonResult<String>> {
        val token = JWTUtil.generateToken("supcoder")
        val response = ResultUtil.success(token) as JsonResult<String>
        return ResponseEntity.ok(response)
    }
}

data class LoginRequest(val username: String?, val password: String?)
