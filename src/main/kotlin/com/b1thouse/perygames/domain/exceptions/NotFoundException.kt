package com.b1thouse.perygames.domain.exceptions

class NotFoundException(
    override val type: TypeError = TypeError.NOT_FOUND,
    override val message: String = type.message
): BaseException()