package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.AuthUser
import com.b1thouse.perygames.domain.gateways.AccountStorageGateway
import com.b1thouse.perygames.resources.storage.repository.AccountRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class AccountStorage(
    private val accountRepository: AccountRepository
): AccountStorageGateway {
    override fun create(user: AuthUser) {
        logger.info("create $user")
        accountRepository.create(user.id, user.login, user.passwrd, user.role.name)
    }

    override fun update(user: AuthUser): AuthUser {
        logger.info("update $user")
        return accountRepository.save(user)
    }

    override fun getByLogin(login: String?): UserDetails? {
        //logger.info("get by login=$login")
        return accountRepository.findByLogin(login)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AccountStorage::class.java)
    }
}