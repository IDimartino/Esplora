package com.esplora.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.esplora.app.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Esplora",
        ) {
            App()
        }
    }
}