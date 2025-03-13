package com.esplora.app.wallet.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val txid: String,
    val vin: List<VinDto>,
    val vout: List<VoutDto>,
    val status: StatusDto
)

@Serializable
data class VinDto(
    val prevout: PrevoutDto
)

@Serializable
data class VoutDto(
    val scriptpubkey_address: String,
    val value: Long
)

@Serializable
data class PrevoutDto(
    val scriptpubkey_address: String
)

@Serializable
data class StatusDto(
    val confirmed: Boolean,
    val block_height: Int? = null
)