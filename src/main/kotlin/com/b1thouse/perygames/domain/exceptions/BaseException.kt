package com.b1thouse.perygames.domain.exceptions

abstract class BaseException(override val cause: Throwable? = null): RuntimeException(cause) {
    abstract val type: TypeError
    abstract override val message: String
}

enum class TypeError(val message: String) {
    NOT_FOUND("Not found")
}