package com.esplora.app.wallet.data.mappers

import com.esplora.app.wallet.data.remote.dto.UtxoDto
import com.esplora.app.wallet.domain.model.Utxo

fun UtxoDto.toUtxo(): Utxo {
    return Utxo(
        txid = txid,
        vout = vout,
        value = value
    )
}