package com.example.qrspot.features.qr_scanner.ui.history

sealed class HistoryUiEvent {
    class OnDeleteHistoryItem(val itemIndex: Int): HistoryUiEvent()
}