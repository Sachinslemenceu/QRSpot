package com.example.qrspot.features.qr_scanner.ui.history

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrspot.features.qr_scanner.domain.models.QrCode
import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory
import com.example.qrspot.features.qr_scanner.domain.repositories.HistoryRepository
import com.example.qrspot.features.qr_scanner.ui.history.ui_models.HistoryUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.Month
import java.time.format.DateTimeFormatter

class HistoryViewModel(
    private val repository: HistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    private val _cachedQrCodes = MutableStateFlow<List<QrCode>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: HistoryUiEvent) {
        when (event) {
            is HistoryUiEvent.OnDeleteHistoryItem -> {
                val qrCode = _cachedQrCodes.asStateFlow().value[event.itemIndex]
                deleteHistory(qrCode)

            }

            is HistoryUiEvent.OnFilterHistory -> {
                Log.d("HistoryViewModel", "Filter History: ${event.type}")
                _uiState.value = _uiState.value.copy(isLoading = true)
                filterHistory(event.type)
            }

            HistoryUiEvent.FetchHistory -> getHistory()
        }
    }

    private fun getHistory() {
        Log.d("HistoryViewModel", "Get History is called")
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHistory().collect { history ->
                _cachedQrCodes.value = history
                Log.d("HistoryViewModel", "History: $history")
                filterHistory(QrCodeCategory.Scanned)
            }
        }
    }

    private fun filterHistory(type: QrCodeCategory) {
        val filteredHistory = _cachedQrCodes.asStateFlow().value.filter { qrCode ->
            qrCode.category == type
        }
        if (filteredHistory.isEmpty()){
            _uiState.value = _uiState.value.copy(
                histories = emptyList(),
                isLoading = false
            )
        } else{
            _uiState.value = _uiState.value.copy(
                histories = filteredHistory.map { qrCode ->
                    val time = format24HrTime(qrCode.time)
                    Log.d("HistoryViewModel", "History TIme: $time")
                    HistoryUiModel(
                        scannedText = qrCode.text,
                        scannedTime = time,
                        type = qrCode.type
                    )
                },
                isLoading = false
            )
        }
    }

    private fun deleteHistory(qrCode: QrCode){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteQrCode(qrCode)
        }
    }
}



private fun format24HrTime(time: LocalDateTime): String{
    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    val formattedTime = time.format(timeFormatter)
    val formattedDate = time.format(dateFormatter)

    return "$formattedDate ,$formattedTime"
}



