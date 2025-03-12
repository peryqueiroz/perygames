package com.b1thouse.perygames.domain.entities

import com.b1thouse.perygames.domain.entities.enums.UserRole
import de.huxhorn.sulky.ulid.ULID
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Table("account")
data class AuthUser(
    @Id val id: String?,
    val login: String,
    val passwrd: String,
    val role: UserRole
): UserDetails {

    constructor(login: String, passwrd: String, role: UserRole) : this(ULID().nextULID(), login, passwrd, role)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return if(this.role == UserRole.ADMIN) mutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_USER"));
        else mutableListOf(SimpleGrantedAuthority("ROLE_USER"));
    }

    override fun getPassword(): String {
        return this.passwrd
    }

    override fun getUsername(): String {
        return login
    }
}