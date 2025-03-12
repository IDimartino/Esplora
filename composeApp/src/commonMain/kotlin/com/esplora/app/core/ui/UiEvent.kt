package com.esplora.app.core.ui

import com.esplora.app.wallet.domain.model.Address

sealed class UiEvent {
    data class Success(
        val balance: Long,
        val addresses: List<Address>
    ): UiEvent()
    data class ShowSnackbar(val message: UiText): UiEvent()
}