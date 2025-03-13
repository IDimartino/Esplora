package com.esplora.app.wallet.data.mappers

import com.esplora.app.wallet.data.remote.dto.PrevoutDto
import com.esplora.app.wallet.data.remote.dto.StatusDto
import com.esplora.app.wallet.data.remote.dto.TransactionDto
import com.esplora.app.wallet.data.remote.dto.VinDto
import com.esplora.app.wallet.data.remote.dto.VoutDto
import com.esplora.app.wallet.domain.model.Prevout
import com.esplora.app.wallet.domain.model.Status
import com.esplora.app.wallet.domain.model.Transaction
import com.esplora.app.wallet.domain.model.Vin
import com.esplora.app.wallet.domain.model.Vout

fun TransactionDto.toTransaction(): Transaction {
    return Transaction(
        txid = txid,
        vin = vin.map { it.toVin() },
        vout = vout.map { it.toVout() },
        status = status.toStatus()
    )
}

fun VinDto.toVin(): Vin {
    return Vin(
        prevout = prevout.toPrevout()
    )
}

fun VoutDto.toVout(): Vout {
    return Vout(
        scriptpubkeyAddress = scriptpubkey_address,
        value = value
    )
}

fun PrevoutDto.toPrevout(): Prevout {
    return Prevout(
        scriptpubkeyAddress = scriptpubkey_address
    )
}

fun StatusDto.toStatus(): Status {
    return Status(
        confirmed = confirmed,
        blockHeight = block_height
    )
}