package com.b1thouse.perygames.resources.storage.repository

import com.b1thouse.perygames.domain.entities.AuthUser
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.transaction.annotation.Transactional

interface AccountRepository: CrudRepository<AuthUser, String> {
    fun findByLogin(login: String?): UserDetails?

    @Modifying
    @Transactional
    @Query("INSERT INTO account (id, login, passwrd, role) VALUES (:id , :login , :passwrd , :role)")
    fun create(id: String?, login: String?, passwrd: String, role: String)
}
