package com.supcoder.core.controller


import com.supcoder.hub.core.model.vo.FakeCaptcha
import com.supcoder.hub.core.model.vo.LoginParams
import com.supcoder.hub.core.model.vo.LoginResult
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/login")
class LoginController {

    @PostMapping("/captcha")
    fun getFakeCaptcha(@RequestParam phone: String): ResponseEntity<FakeCaptcha> {
        val fakeCaptcha = FakeCaptcha(code = 12345, status = "success")
        return ResponseEntity.ok(fakeCaptcha)
    }

    @PostMapping("/account")
    fun login(@RequestBody loginParams: LoginParams): ResponseEntity<LoginResult> {
        val loginResult = LoginResult(status = "ok", type = "account", currentAuthority = "admin")
        return ResponseEntity.ok(loginResult)
    }
}
