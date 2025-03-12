package com.b1thouse.perygames.application.web.controller

import com.b1thouse.perygames.domain.services.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal

@Controller
@RequestMapping("v1/user")
class UserController(
    private val userService: AccountService
) {
    @PostMapping("/{userId}/deposit")
    fun deposit(@PathVariable("userId") userId: String,
                      @RequestParam("amount") amount: BigDecimal): ResponseEntity<String> {
        userService.deposit(userId, amount)
        return ResponseEntity.status(HttpStatus.OK).body("Amount deposited successfully")
    }

/*    @PostMapping("/{userId}/withdraw")
    fun withdraw(@PathVariable("userId") userId: String,
                      @RequestParam("amount") amount: BigDecimal): ResponseEntity<String> {
        val response = userService.withdraw(userId, amount).toUserResponse
        return ResponseEntity.status(HttpStatus.OK).body("Amount deposited successfully")
    }
 */
}