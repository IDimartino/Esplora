package com.esplora.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.esplora.app.ui.theme.EsploraTheme
import com.esplora.app.wallet.presentation.WalletScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    EsploraTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            WalletScreen(
                innerPadding = innerPadding
            )
        }
    }
}