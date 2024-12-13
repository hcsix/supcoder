package com.supcoder.system.dashboard.model.vo

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
    val password: String
)

data class LoginResult(
    val status: String,
    val type: String,
    val currentAuthority: String
)