package com.b1thouse.perygames.domain.exceptions

class BetAlreadyPendingException(
    override val type: TypeError = TypeError.ALREADY_BET_PENDING,
    override val message: String = type.message
): BaseException()