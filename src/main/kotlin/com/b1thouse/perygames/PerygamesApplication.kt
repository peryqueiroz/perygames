package com.b1thouse.perygames

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PerygamesApplication

fun main(args: Array<String>) {
	runApplication<PerygamesApplication>(*args)
}
