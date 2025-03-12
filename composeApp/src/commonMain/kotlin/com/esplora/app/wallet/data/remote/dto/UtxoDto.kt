package com.esplora.app.wallet.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UtxoDto(
    val txid: String,
    val vout: Int,
    val value: Long
)