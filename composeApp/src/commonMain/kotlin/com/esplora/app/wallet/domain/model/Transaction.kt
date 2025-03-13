package com.esplora.app.wallet.domain.model

data class Transaction(
    val txid: String,
    val vin: List<Vin>,
    val vout: List<Vout>,
    val status: Status
)

data class Vin(
    val prevout: Prevout
)

data class Vout(
    val scriptpubkeyAddress: String,
    val value: Long
)

data class Prevout(
    val scriptpubkeyAddress: String
)

data class Status(
    val confirmed: Boolean,
    val blockHeight: Int? = null
)