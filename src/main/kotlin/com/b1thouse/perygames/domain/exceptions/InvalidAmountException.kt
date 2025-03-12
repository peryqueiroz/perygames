package com.b1thouse.perygames.domain.exceptions

class InvalidAmountException(
    override val type: TypeError = TypeError.INVALID_AMOUNT,
    override val message: String = type.message
): BaseException()