package com.b1thouse.perygames.application.web.exception

import com.b1thouse.perygames.domain.exceptions.BaseException
import com.b1thouse.perygames.domain.exceptions.ErrorResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    fun defaultHandler(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error("Exception Handled ex=$ex")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    type = HttpStatus.INTERNAL_SERVER_ERROR.name,
                    message = ex.message,
                    details = listOf(mapOf<String, Any>())
                )
            )
    }

    @ExceptionHandler(value = [BaseException::class])
    fun baseExceptionHandler(ex: BaseException): ResponseEntity<ErrorResponse> {
        logger.error("BaseException Handled ex=$ex")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    type = ex.type.name,
                    message = ex.message,
                    details = listOf(mapOf<String, Any>())
                )
            )

    }


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(ExceptionHandler::class.java)
    }
}