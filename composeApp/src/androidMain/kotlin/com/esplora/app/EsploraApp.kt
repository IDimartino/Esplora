package com.esplora.app

import android.app.Application
import com.esplora.app.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class EsploraApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@EsploraApp)
        }

        Napier.base(DebugAntilog())
    }
}