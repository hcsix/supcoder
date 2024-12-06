package com.supcoder.core.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.Date
/**
 * JWTUtil
 *
 * @author lee
 * @date 2024/12/6
 */
object JWTUtil {

//    private const val SECRET_KEY = "G7bW9mQ5vPzX2rL8tNqK4fJwH1cR6dY0eZuA3nVbTmS="

    private const val EXPIRATION_TIME = 864_000_000L // 10 days in milliseconds

    val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)

    fun generateToken(username: String): String {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
    }
}