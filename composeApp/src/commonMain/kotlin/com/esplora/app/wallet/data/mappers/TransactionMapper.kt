package com.esplora.app.wallet.data.mappers

import com.esplora.app.wallet.data.remote.dto.TransactionDto
import com.esplora.app.wallet.domain.model.Transaction

fun TransactionDto.toTransaction(): Transaction {
    return Transaction(
        txid = txid,
        vin = vin,
        vout = vout,
        status = status
    )
}