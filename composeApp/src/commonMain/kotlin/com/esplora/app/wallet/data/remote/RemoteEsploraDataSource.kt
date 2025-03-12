package com.esplora.app.wallet.data.remote

import com.esplora.app.BuildKonfig
import com.esplora.app.core.data.remote.networking.constructUrl
import com.esplora.app.core.data.remote.networking.safeCall
import com.esplora.app.core.domain.util.NetworkError
import com.esplora.app.core.domain.util.Result
import com.esplora.app.core.domain.util.map
import com.esplora.app.wallet.data.mappers.toTransaction
import com.esplora.app.wallet.data.mappers.toUtxo
import com.esplora.app.wallet.data.remote.dto.TransactionDto
import com.esplora.app.wallet.data.remote.dto.UtxoDto
import com.esplora.app.wallet.domain.model.Address
import com.esplora.app.wallet.domain.model.Transaction
import com.esplora.app.wallet.domain.model.Utxo
import com.esplora.app.wallet.domain.remote.EsploraDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteEsploraDataSource(
    private val httpClient: HttpClient
) : EsploraDataSource {
    override suspend fun getAddress(address: String): Result<List<Address>, NetworkError> {
        TODO("Implement mempool")
    }

    override suspend fun getUtxos(address: String): Result<List<Utxo>, NetworkError> {
        return safeCall<List<UtxoDto>> {
            httpClient.get(
                urlString = constructUrl(BuildKonfig.BASE_URL, "/address/$address/utxo")
            )
        }.map { response ->
            response.map {
                it.toUtxo()
            }
        }
    }

    override suspend fun getTransactions(address: String): Result<List<Transaction>, NetworkError> {
        return safeCall<List<TransactionDto>> {
            httpClient.get(
                urlString = constructUrl(BuildKonfig.BASE_URL, "/address/$address/txs")
            )
        }.map { response ->
            response.map { it.toTransaction() }
        }
    }
}