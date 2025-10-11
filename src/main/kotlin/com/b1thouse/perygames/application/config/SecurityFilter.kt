package com.b1thouse.perygames.application.config

import com.b1thouse.perygames.domain.gateways.AccountStorageGateway
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class SecurityFilter(
    private val tokenService: TokenService,
    private val accountStorageGateway: AccountStorageGateway
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = this.recoverToken(request)
            if (token != null) {
                val login = tokenService.validateToken(token)
                val user = accountStorageGateway.getByLogin(login)

                if (user != null) {
                    val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                    SecurityContextHolder.getContext().authentication = authentication
                    println("✅ User authenticated: $login")
                } else {
                    println("❌ User not found for login: $login")
                }
            } else {
                println("ℹ️ No token found in request")
            }
        } catch (ex: Exception) {
            println("❌ Exception during authentication: ${ex.message}")
        }

        filterChain.doFilter(request, response)
    }


    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization") ?: return null
        return authHeader.replace("Bearer ", "")
    }
}