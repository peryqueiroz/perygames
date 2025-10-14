package com.b1thouse.perygames.application.web.controller

import com.b1thouse.perygames.application.web.dto.CreatePlayerUserDTO
import com.b1thouse.perygames.domain.entities.enums.TransactionType
import com.b1thouse.perygames.domain.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.math.BigDecimal

@Controller
@RequestMapping("v1/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/{userId}/deposit")
    fun deposit(@PathVariable("userId") userId: String,
                      @RequestParam("amount") amount: BigDecimal): ResponseEntity<String> {
        userService.deposit(userId, amount, TransactionType.DEPOSIT)
        return ResponseEntity.status(HttpStatus.OK).body("Amount deposited successfully")
    }

    @PostMapping
    fun create(@RequestBody createPlayerUserDTO: CreatePlayerUserDTO): ResponseEntity<String> {
        userService.create(createPlayerUserDTO)
        return ResponseEntity.ok("Player and User created")
    }

    @GetMapping("/{userId}/balance")
    fun getBalanceById(@PathVariable("userId") userId: String) : ResponseEntity<BigDecimal> {
        return ResponseEntity.ok(userService.getById(userId).balance)
    }

/*    @PostMapping("/{userId}/withdraw")
    fun withdraw(@PathVariable("userId") userId: String,
                      @RequestParam("amount") amount: BigDecimal): ResponseEntity<String> {
        val response = userService.withdraw(userId, amount).toUserResponse
        return ResponseEntity.status(HttpStatus.OK).body("Amount deposited successfully")
    }
 */
}