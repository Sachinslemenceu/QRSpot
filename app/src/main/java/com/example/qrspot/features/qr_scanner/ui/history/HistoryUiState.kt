package com.example.qrspot.features.qr_scanner.ui.history

import com.example.qrspot.features.qr_scanner.ui.history.ui_models.HistoryUiModel

data class HistoryUiState(
    val isLoading: Boolean = false,
    val histories: List<HistoryUiModel> = emptyList()
)
