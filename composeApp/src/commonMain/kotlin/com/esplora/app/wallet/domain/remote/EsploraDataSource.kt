package com.esplora.app.wallet.domain.remote

import com.esplora.app.core.domain.util.NetworkError
import com.esplora.app.core.domain.util.Result
import com.esplora.app.wallet.domain.model.Address
import com.esplora.app.wallet.domain.model.Transaction
import com.esplora.app.wallet.domain.model.Utxo

interface EsploraDataSource {
    suspend fun getAddress(address: String): Result<List<Address>, NetworkError>
    suspend fun getUtxos(address: String): Result<List<Utxo>, NetworkError>
    suspend fun getTransactions(address: String): Result<List<Transaction>, NetworkError>
}