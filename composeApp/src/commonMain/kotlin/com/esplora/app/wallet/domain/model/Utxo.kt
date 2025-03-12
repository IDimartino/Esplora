package com.esplora.app.wallet.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Utxo(
    val txid: String,
    val vout: Int,
    val value: Long
)