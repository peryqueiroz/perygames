package com.b1thouse.perygames.domain.exceptions

class InsufficientBalanceException(
    override val type: TypeError = TypeError.INSUFFICIENT_BALANCE,
    override val message: String = type.message
): BaseException()