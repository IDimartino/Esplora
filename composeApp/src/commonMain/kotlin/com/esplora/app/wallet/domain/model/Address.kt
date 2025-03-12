package com.esplora.app.wallet.domain.model

data class Address(
    val id: String,
    val balance: Long,
    val transactions: List<Transaction>
)