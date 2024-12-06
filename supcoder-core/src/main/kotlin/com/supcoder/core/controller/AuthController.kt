package com.supcoder.core.controller

import com.supcoder.core.model.User
import com.supcoder.core.service.UserService
import com.supcoder.core.util.JWTUtil
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * AuthController
 *
 * @author lee
 * @date 2024/12/6
 */
@RestController
@RequestMapping("/api/auth")
class AuthController{
    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String> {
        user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())
        userService.save(user)
        return ResponseEntity.ok("User registered successfully")
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        return ResponseEntity.ok("Logged out successfully")
    }

    /**
     * 登录接口
     * @param loginRequest 登录请求参数
     * @return 登录成功返回token，否则返回错误信息
     */
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        val user = userService.findByUsername(loginRequest.username)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found")

        if (!BCrypt.checkpw(loginRequest.password, user.password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password")
        }

        val token = JWTUtil.generateToken(user.username)
        return ResponseEntity.ok(token)
    }
}

data class LoginRequest(val username: String, val password: String)
