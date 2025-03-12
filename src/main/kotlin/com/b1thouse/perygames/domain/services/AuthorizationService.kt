package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.domain.exceptions.NotFoundException
import com.b1thouse.perygames.domain.gateways.AccountStorageGateway
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val accountStorageGateway: AccountStorageGateway
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return accountStorageGateway.getByLogin(username) ?: throw NotFoundException(message = "User $username not found")
    }
}