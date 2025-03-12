package com.esplora.app.di

import com.esplora.app.core.data.remote.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule = module {
    single {
        createHttpClient(OkHttp.create())
    }
}