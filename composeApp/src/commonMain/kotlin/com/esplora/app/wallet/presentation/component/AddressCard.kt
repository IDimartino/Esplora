package com.esplora.app.wallet.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.esplora.app.ui.theme.LocalSpacing
import com.esplora.app.wallet.domain.model.Address
import esplora.composeapp.generated.resources.Res
import esplora.composeapp.generated.resources.wallet_screen_address_number_tx
import esplora.composeapp.generated.resources.wallet_screen_address_title
import esplora.composeapp.generated.resources.wallet_screen_address_tx
import esplora.composeapp.generated.resources.wallet_screen_address_tx_confirmed
import esplora.composeapp.generated.resources.wallet_screen_address_tx_unconfirmed
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddressCard(
    isLoading: Boolean = false,
    addressData: Address
) {
    val spacing = LocalSpacing.current
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.spaceSmall)
            .clickable { expanded = !expanded }
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = spacing.spaceExtraSmall)
    ) {
        Column(modifier = Modifier.padding(spacing.spaceMedium)) {
            // Always show the address.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(Res.string.wallet_screen_address_title) +" ${addressData.id}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }

            if (expanded) {
                if (isLoading && addressData.transactions.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                spacing.spaceLarge,
                                spacing.spaceLarge,
                                spacing.spaceMedium,
                                0.dp
                            )
                            .size(spacing.sizeLarge)
                    )
                } else {
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                    Divider(modifier = Modifier.background(Color.White))
                    Text(
                        text = stringResource(Res.string.wallet_screen_address_number_tx) + " ${addressData.transactions.size}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(
                            top = spacing.spaceExtraSmall,
                            bottom = spacing.spaceExtraSmall
                        )
                    )
                    Divider(
                        modifier = Modifier.padding(bottom = spacing.spaceSmall)
                            .background(Color.White)
                    )
                    addressData.transactions.forEach { transaction ->
                        Text(
                            text = stringResource(Res.string.wallet_screen_address_tx) +
                                    " (${
                                        if (transaction.status.confirmed)
                                            stringResource(Res.string.wallet_screen_address_tx_confirmed)
                                        else
                                            stringResource(Res.string.wallet_screen_address_tx_unconfirmed)
                                    }) ${transaction.txid}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = spacing.spaceExtraSmall)
                        )
                    }
                }
            }
        }
    }
}