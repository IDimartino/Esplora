package com.esplora.app.di

import com.esplora.app.wallet.data.remote.RemoteEsploraDataSource
import com.esplora.app.wallet.domain.remote.EsploraDataSource
import com.esplora.app.wallet.presentation.WalletViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val appModule = module {
    singleOf(::RemoteEsploraDataSource).bind<EsploraDataSource>()

    viewModelOf(::WalletViewModel)
}