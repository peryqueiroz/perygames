package com.b1thouse.perygames.resources.storage

import com.b1thouse.perygames.domain.entities.TransactionRecord
import com.b1thouse.perygames.domain.gateways.TransactionRecordStorageGateway
import com.b1thouse.perygames.resources.storage.repository.TransactionRecordRepository
import com.b1thouse.perygames.resources.storage.repository.toTable
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TransactionRecordStorage(
    private val transactionRepository: TransactionRecordRepository
): TransactionRecordStorageGateway {

    override fun create(transactionRecord: TransactionRecord): TransactionRecord {
        logger.info("create $transactionRecord")
        return transactionRepository.save(transactionRecord.toTable(new = true)).toDomain()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TransactionRecordStorage::class.java)
    }
}