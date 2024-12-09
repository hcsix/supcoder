package com.supcoder.core.model.vo

/**
 * Login
 *
 * @author lee
 * @date 2024/12/9
 */

data class FakeCaptcha(
    val code: Int,
    val status: String
)
data class LoginParams(
    val username: String,
    val password: String,
    val autoLogin: Boolean,
    val type: String
)

data class LoginResult(
    val status: String,
    val type: String,
    val currentAuthority: String
)