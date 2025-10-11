package com.b1thouse.perygames.application.web.controller

import com.b1thouse.perygames.application.config.TokenService
import com.b1thouse.perygames.application.web.dto.AuthenticationDTO
import com.b1thouse.perygames.application.web.dto.LoginResponse
import com.b1thouse.perygames.application.web.dto.RegisterDTO
import com.b1thouse.perygames.domain.entities.AuthUser
import com.b1thouse.perygames.domain.gateways.AccountStorageGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthenticationController(
    @Autowired
    private val authenticationManager: AuthenticationManager,
    private val authStorageGateway: AccountStorageGateway,
    private val tokenService: TokenService
) {

    @GetMapping("/login")
    fun login(@RequestBody data: AuthenticationDTO): ResponseEntity<LoginResponse> {
        val credentials = UsernamePasswordAuthenticationToken(data.login, data.password)
        val auth = authenticationManager.authenticate(credentials)
        val token = tokenService.generateToken(auth.principal as AuthUser)

        return ResponseEntity.ok(LoginResponse(token))
    }

    @PostMapping("/register")
    fun register(@RequestBody registerDTO: RegisterDTO): ResponseEntity<String> {
        val auth = authStorageGateway.getByLogin(registerDTO.login)
        if(authStorageGateway.getByLogin(registerDTO.login) != null) return ResponseEntity.badRequest().build()

        logger.info("auth $auth")
        val encryptedPassword = BCryptPasswordEncoder().encode(registerDTO.password)
        val newUser = AuthUser(registerDTO.login, encryptedPassword, registerDTO.role)

        authStorageGateway.create(newUser)
        return ResponseEntity.ok().build()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AuthenticationController::class.java)
    }
}