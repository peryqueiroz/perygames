package com.b1thouse.perygames.application.job

import com.b1thouse.perygames.domain.services.BetService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class CheckBetPendingJob(
    private val betService: BetService
) {

    @Scheduled(cron = "0 */2 * * * *")
    fun checkBetPending() {
        logger.info("CheckBetPendingJob has started")
        betService.checkAllBetPendingToComplete()
        logger.info("CheckBetPendingJob has finished")
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CheckBetPendingJob::class.java)
    }
}