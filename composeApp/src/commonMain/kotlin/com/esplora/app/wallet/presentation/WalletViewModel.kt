package com.esplora.app.wallet.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esplora.app.core.domain.util.onError
import com.esplora.app.core.domain.util.onSuccess
import com.esplora.app.wallet.domain.model.Address
import com.esplora.app.wallet.domain.model.AddressBook
import com.esplora.app.wallet.domain.model.Transaction
import com.esplora.app.wallet.domain.remote.EsploraDataSource
import io.github.aakira.napier.Napier
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class WalletViewModel(
    private val esploraDataSource: EsploraDataSource,
) : ViewModel() {

    companion object {
        const val POLL_INTERVAL = 30000L
        const val SATOSHI_TO_BTC = 100_000_000.0
    }

    private val addressBook = AddressBook.testnetAddresses

    private val addresses: List<Address> = addressBook.map { address ->
        Address(id = address, 0, listOf())
    }

    private val _state = MutableStateFlow(AddressListState())
    val state = _state
        .onStart { fetchAllData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(0L),
            AddressListState(
                isLoading = true,
                addresses = addresses
            )
        )

    // Refresh the datas every 30 seconds
    private fun tickerFlow(pollInterval: Long): Flow<Unit> = flow {
        while (true) {
            delay(pollInterval)
            emit(Unit)
        }
    }

    fun pullToRefresh() {
        viewModelScope.launch {
            _state.update {
                state.value.copy(
                    isLoading = true,
                )
            }

            fetchAllData()
        }
    }

    // TODO: Use a different way to refresh the data, using websockets if possible
    init {
        viewModelScope.launch {
            _state.update { _state.value }

            tickerFlow(POLL_INTERVAL).collect {
                fetchAllData()
            }
        }
    }

    // TODO: Use Usecases when possible
    private suspend fun fetchAllData() {
        val results = mutableListOf<Address>()
        val mutex = Mutex()
        val addressDataList = MutableStateFlow<List<Address>>(emptyList())

        coroutineScope {
            addresses.forEach { address ->
                launch {
                    val data = getAddressTransactions(address)
                    mutex.withLock {
                        results.add(data)
                    }
                }
            }
        }
        addressDataList.value = results.sortedByDescending { it.transactions.size }

        _state.update {
            state.value.copy(
                isLoading = false,
                balance = calculateTotalBalance(addressDataList),
                addresses = addressDataList.value
            )
        }
    }

    // TODO: Manage unconfrmed and confirmed transactions
    // TODO: Use mempool to update only the unconfirmed transactions
    // TODO: Use Room to store the transactions
    // TODO: Manage Errors and Exceptions by sending a message to the UI
    private suspend fun getAddressTransactions(address: Address): Address {

        val balanceAdress = mutableStateOf(0L)
        var transactionsAddress = mutableListOf<Transaction>()
        esploraDataSource
            .getUtxos(address = address.id)
            .onSuccess { xtos ->
                balanceAdress.value = xtos.sumOf { it.value }
            }
            .onError { e ->
                Napier.v("Error: " + e.name)
            }
        esploraDataSource
            .getTransactions(address.id)
            .onSuccess { transactions ->
                transactionsAddress = transactions.toMutableList()
            }
            .onError { e ->
                Napier.v("Error: " + e.name)
            }
        return address.copy(
            id = address.id,
            balance = balanceAdress.value,
            transactions = transactionsAddress
        )
    }

    // Calculates total confirmed balance
    private fun calculateTotalBalance(addressList: MutableStateFlow<List<Address>>): Double {
        return addressList.value.sumOf {
            it.balance
        } / SATOSHI_TO_BTC
    }
}