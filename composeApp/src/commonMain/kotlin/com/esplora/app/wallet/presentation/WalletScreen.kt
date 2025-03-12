package com.esplora.app.wallet.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.esplora.app.ui.theme.LocalSpacing
import com.esplora.app.wallet.presentation.component.AddressCard
import esplora.composeapp.generated.resources.Res
import esplora.composeapp.generated.resources.wallet_screen_satoshis
import esplora.composeapp.generated.resources.wallet_screen_total_balance
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: WalletViewModel = koinViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state.collectAsStateWithLifecycle()
    val stateRefresh = rememberPullToRefreshState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(
                top =
                    spacing.spaceMedium +
                            innerPadding.calculateTopPadding(),
                start = spacing.spaceLarge,
                end = spacing.spaceLarge,
                bottom = innerPadding.calculateBottomPadding()
            )
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = spacing.spaceMedium),
            elevation = spacing.spaceSmall
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.wallet_screen_total_balance),
                        modifier = Modifier.padding(spacing.spaceMedium)
                            .weight(1f),
                    )
                    if (state.value.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(spacing.spaceMedium)
                                .size(spacing.sizeMedium),
                            strokeWidth = 1.dp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                Text(
                    text = "${state.value.balance} " + stringResource(Res.string.wallet_screen_satoshis),
                    modifier = Modifier.padding(
                        start = spacing.spaceMedium,
                        end = spacing.spaceMedium,
                        bottom = spacing.spaceMedium
                    )
                        .align(Alignment.End),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        PullToRefreshBox(
            isRefreshing = state.value.isLoading,
            onRefresh = {
                viewModel.pullToRefresh()
            },
            modifier = modifier,
            state = stateRefresh,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = spacing.spaceSmall)
            ) {
                items(state.value.addresses) { addressData ->
                    AddressCard(
                        isLoading = state.value.isLoading,
                        addressData = addressData
                    )
                }
            }
        }
    }
}