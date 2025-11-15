package com.example.qrspot.features.qr_scanner.domain.repositories

import com.example.qrspot.core.database.models.QrCodeEntity
import com.example.qrspot.features.qr_scanner.domain.models.QrCode
import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    suspend fun getHistory(type: QrCodeCategory): Flow<List<QrCodeEntity>>

    suspend fun saveQrCode(qrCode: QrCode)

    suspend fun deleteQrCode(qrCode: QrCode)

}