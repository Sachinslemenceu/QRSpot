package com.example.qrspot.features.qr_scanner.domain.models

import java.time.LocalDateTime

data class QrCode(
    val text: String,
    val time: LocalDateTime,
    val type: String,
    val category: QrCodeCategory
)