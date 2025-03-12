package com.esplora.app

import androidx.compose.ui.window.ComposeUIViewController
import com.esplora.app.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}