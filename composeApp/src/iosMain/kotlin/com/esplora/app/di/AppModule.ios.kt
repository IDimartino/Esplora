package com.esplora.app.di

import com.esplora.app.core.data.remote.networking.createHttpClient
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformModule = module {
    single {
        createHttpClient(Darwin.create())
    }
}