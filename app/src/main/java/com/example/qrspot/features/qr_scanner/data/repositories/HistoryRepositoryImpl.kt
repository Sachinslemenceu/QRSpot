package com.example.qrspot.features.qr_scanner.data.repositories

import android.util.Log
import androidx.compose.runtime.collectAsState
import com.example.qrspot.MyApp
import com.example.qrspot.core.database.models.QrCodeEntity
import com.example.qrspot.features.qr_scanner.data.mappers.toQrcode
import com.example.qrspot.features.qr_scanner.domain.models.QrCode
import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory
import com.example.qrspot.features.qr_scanner.domain.repositories.HistoryRepository
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList


class HistoryRepositoryImpl : HistoryRepository{
    private val realm = MyApp.realm

    override suspend fun getHistory(): Flow<List<QrCode>> {
        val qrCodes = realm
            .query<QrCodeEntity>()
            .asFlow()
            .map { resultsChange ->
                resultsChange.list.toList()
                .map { qrCodeEntity ->
                    qrCodeEntity.toQrcode()
                }
            }
        Log.d("HistoryRepositoryImpl", "getHistory: ${qrCodes.first().size}")
        return qrCodes
    }

    override suspend fun saveQrCode(qrCode: QrCode) {
        Log.d("HistoryRepositoryImpl", "saveQrCode: $qrCode")
        realm.write {
            val qrCode = QrCodeEntity().apply {
                scannedText = qrCode.text
                type = qrCode.type
                category = qrCode.category.toString()
            }
            copyToRealm(qrCode, UpdatePolicy.ALL)
        }
    }

    override suspend fun deleteQrCode(qrCode: QrCode) {
        val code = realm.query<QrCodeEntity>("id == $0", qrCode.id).find().first()
        realm.write {
            val latestQrCode = findLatest(code)
            latestQrCode?.let { delete(it) }
        }
    }

}
