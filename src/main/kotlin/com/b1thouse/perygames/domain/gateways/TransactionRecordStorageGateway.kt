package com.b1thouse.perygames.domain.gateways

import com.b1thouse.perygames.domain.entities.TransactionRecord

interface TransactionRecordStorageGateway {
    fun create(transactionRecord: TransactionRecord): TransactionRecord
}