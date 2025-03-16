package com.b1thouse.perygames.domain.exceptions

abstract class BaseException(override val cause: Throwable? = null): RuntimeException(cause) {
    abstract val type: TypeError
    abstract override val message: String
}

enum class TypeError(val message: String) {
    NOT_FOUND("Not found"),
    INVALID_AMOUNT("Invalid Amount"),
    ALREADY_BET_PENDING("User already has bet on going"),
    INVALID_TOKEN("Invalid Token"),
    INSUFFICIENT_BALANCE("Balance has insufficient amount"),
    USER_NOT_ACTIVE("User not active")
}