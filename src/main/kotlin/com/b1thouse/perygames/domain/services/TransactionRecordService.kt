package com.b1thouse.perygames.domain.services

import com.b1thouse.perygames.domain.entities.TransactionRecord
import com.b1thouse.perygames.domain.gateways.TransactionRecordStorageGateway
import org.springframework.stereotype.Service

@Service
class TransactionRecordService(
    private val transactionRecordStorageGateway: TransactionRecordStorageGateway
) {

    fun create(transactionRecord: TransactionRecord) {
        transactionRecordStorageGateway.create(transactionRecord)
    }
}