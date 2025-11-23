package com.example.qrspot.features.qr_scanner.domain.models

import org.mongodb.kbson.ObjectId
import java.time.LocalDateTime

data class QrCode(
    val id: ObjectId? = null,
    val text: String,
    val time: LocalDateTime,
    val type: String,
    val category: QrCodeCategory
)