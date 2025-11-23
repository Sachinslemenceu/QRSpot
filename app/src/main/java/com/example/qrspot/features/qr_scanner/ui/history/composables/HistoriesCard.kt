package com.example.qrspot.features.qr_scanner.ui.history.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.qrspot.features.qr_scanner.ui.history.HistoryUiEvent
import com.example.qrspot.features.qr_scanner.ui.history.HistoryUiState
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500

@Composable
fun HistoriesCard(
    uiState: HistoryUiState,
    onEvent: (HistoryUiEvent) -> Unit = {},
    onViewLink: (String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        if (uiState.isLoading) {
            Spacer(Modifier.weight(0.2f))
            CircularProgressIndicator(
                color = yellow500,
                strokeWidth = 5.dp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.weight(1f))
        } else if (uiState.histories.isEmpty()) {
            Spacer(Modifier.weight(0.2f))
            Text(
                text = "No History Found",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.weight(1f))
        } else {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = darkGrey500
                ),
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 20.dp
                ),
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                uiState.histories.forEachIndexed { index, historyItem ->
                    HistoryCard(
                        scannedText = historyItem.scannedText,
                        scannedTime = historyItem.scannedTime,
                        historyType = historyItem.type,
                        onDeleteClicked = {
                            onEvent(HistoryUiEvent.OnDeleteHistoryItem(itemIndex = index))
                        },
                        onLinkClicked = {
                            onViewLink(
                                historyItem.scannedText,
                                "Scanned",
                                historyItem.scannedTime
                            )
                        }
                    )

                }
            }
        }
    }
}