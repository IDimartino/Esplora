package com.esplora.app.wallet.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val txid: String,
    val vin: List<Vin>,
    val vout: List<Vout>,
    val status: Status
)

@Serializable
data class Vin(
    val prevout: Prevout
)

@Serializable
data class Vout(
    val scriptpubkeyAddress: String,
    val value: Long
)

@Serializable
data class Prevout(
    val scriptpubkeyAddress: String
)

@Serializable
data class Status(
    val confirmed: Boolean,
    val blockHeight: Int? = null
)