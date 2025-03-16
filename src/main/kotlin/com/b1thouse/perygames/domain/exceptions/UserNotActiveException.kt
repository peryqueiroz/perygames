package com.b1thouse.perygames.domain.exceptions

class UserNotActiveException(
    override val type: TypeError = TypeError.USER_NOT_ACTIVE,
    override val message: String = type.message
): BaseException()