package com.example.qrspot.features.qr_scanner.ui.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory
import com.example.qrspot.features.qr_scanner.domain.repositories.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: HistoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: HistoryUiEvent) {
        when (event) {
            is HistoryUiEvent.OnDeleteHistoryItem -> {

            }
        }
    }

    fun getHistory(type: QrCodeCategory) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHistory(type).collect { history ->
                Log.d("HistoryViewModel", "History: $history")
            }
        }
    }
}



