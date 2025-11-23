package com.example.qrspot.features.qr_scanner.data.mappers

import com.example.qrspot.core.database.models.QrCodeEntity
import com.example.qrspot.features.qr_scanner.data.utils.ObjectIdDateTimeConvertor
import com.example.qrspot.features.qr_scanner.domain.models.QrCode
import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory

fun QrCodeEntity.toQrcode(): QrCode{
    return QrCode(
        id = this.id,
        text = this.scannedText,
        type = this.type,
        category = when(this.category){
            "Scanned" -> QrCodeCategory.Scanned
            "Generated" -> QrCodeCategory.Generated
            else -> QrCodeCategory.Scanned
        },
        time = ObjectIdDateTimeConvertor.objectIdToLocalDateTime(this.id)
    )
}