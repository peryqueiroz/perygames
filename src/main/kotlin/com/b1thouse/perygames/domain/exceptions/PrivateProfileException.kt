package com.b1thouse.perygames.domain.exceptions

class PrivateProfileException(
    override val type: TypeError = TypeError.PRIVATE_PROFILE,
    override val message: String = type.message
): BaseException()