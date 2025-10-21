package com.b1thouse.perygames.application.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.b1thouse.perygames.application.web.dto.LoginResponse
import com.b1thouse.perygames.domain.entities.AuthUser
import com.b1thouse.perygames.domain.exceptions.InvalidTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService(
    @Value("\${api.security.token.secret}")
    private val secret: String
) {

    fun generateToken(user: AuthUser): LoginResponse {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            val token = JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.username)
                .withExpiresAt(genExpirationDate())
                .sign(algorithm)
            LoginResponse(token = token, accountId = user.id)
        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error while generating token", exception)
        }
    }

    fun validateToken(token: String): String {
        return try {
            val algorithm = Algorithm.HMAC256(secret)
            JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .subject
        } catch (exception: JWTVerificationException) {
            throw InvalidTokenException(message = "Token is invalid or expired.")
        }
    }

    private fun genExpirationDate(): Instant {
        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"))
    }
}