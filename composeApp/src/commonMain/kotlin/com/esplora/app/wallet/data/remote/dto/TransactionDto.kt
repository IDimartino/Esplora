package com.esplora.app.wallet.data.remote.dto

import com.esplora.app.wallet.domain.model.Status
import com.esplora.app.wallet.domain.model.Vin
import com.esplora.app.wallet.domain.model.Vout
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val txid: String,
    val vin: List<Vin>,
    val vout: List<Vout>,
    val status: Status
)