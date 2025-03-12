package com.esplora.app.wallet.presentation

import androidx.compose.runtime.Immutable
import com.esplora.app.wallet.domain.model.Address

@Immutable
data class AddressListState(
    val isLoading: Boolean = false,
    val balance: Double = 0.0,
    val addresses: List<Address> = emptyList()
)
