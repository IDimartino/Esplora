package com.esplora.app.wallet.data.remote

import com.esplora.app.core.domain.util.NetworkError
import com.esplora.app.core.domain.util.Result
import com.esplora.app.wallet.domain.model.Address
import com.esplora.app.wallet.domain.model.Status
import com.esplora.app.wallet.domain.model.Transaction
import com.esplora.app.wallet.domain.model.Utxo
import com.esplora.app.wallet.domain.remote.EsploraDataSource

// Fake implementation for testing:
class FakeEsploraDataSource : EsploraDataSource {
    override suspend fun getAddress(address: String): Result<List<Address>, NetworkError> {
        TODO("Not yet implemented")
    }

    override suspend fun getUtxos(address: String): Result<List<Utxo>, NetworkError> {
        // Return a fixed utxo value for testing purposes.
        return Result.Success(listOf(Utxo(
            value = 100_000_000L,
            txid = "",
            vout = 0
        )))
    }
    
    override suspend fun getTransactions(address: String): Result<List<Transaction>, NetworkError> {
        // Return a dummy list of transactions
        return Result.Success(listOf(Transaction(
            txid = "",
            vin = listOf(),
            vout = listOf(),
            status = Status(confirmed = true, blockHeight = 123456)
        )))
    }
}