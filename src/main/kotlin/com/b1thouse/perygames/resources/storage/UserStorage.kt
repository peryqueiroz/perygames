package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.User
import com.b1thouse.perygames.domain.gateways.UserStorageGateway
import com.b1thouse.perygames.resources.storage.repository.UserRepository
import com.b1thouse.perygames.resources.storage.repository.toTable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserStorage(
    private val userRepository: UserRepository
) : UserStorageGateway {
    override fun create(user: User): User {
        return userRepository.save(user.toTable(new = true)).toDomain()
    }

    override fun update(user: User): User {
        return userRepository.save(user.toTable()).toDomain()
    }

    override fun getById(id: String): User? {
        return userRepository.findByIdOrNull(id)?.toDomain()
    }

}