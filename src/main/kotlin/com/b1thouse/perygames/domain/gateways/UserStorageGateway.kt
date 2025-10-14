package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.UserBet
import java.math.BigDecimal

interface UserStorageGateway {

    fun create(user: UserBet): UserBet
    fun update(user: UserBet): UserBet
    fun findById(id: String): UserBet?
    fun findByAccountId(accountId: String): UserBet?
    fun depositBalance(userId: String, amount: BigDecimal)
    fun withdrawBalance(userId: String, amount: BigDecimal)
}