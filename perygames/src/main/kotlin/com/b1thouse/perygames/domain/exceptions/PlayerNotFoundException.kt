package com.b1thouse.perygames.domain.exceptions

data class PlayerNotFoundException(
    override val type: TypeError = TypeError.NOT_FOUND,
    override val message: String = type.message
): BaseException()