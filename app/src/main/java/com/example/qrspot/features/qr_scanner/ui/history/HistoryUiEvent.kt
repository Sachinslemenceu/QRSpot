package com.example.qrspot.features.qr_scanner.ui.history

import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory

sealed class HistoryUiEvent {
    class OnDeleteHistoryItem(val itemIndex: Int): HistoryUiEvent()
    class OnFilterHistory(val type: QrCodeCategory): HistoryUiEvent()
    object FetchHistory: HistoryUiEvent()
}

