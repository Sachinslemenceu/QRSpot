package com.example.qrspot.features.qr_scanner.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val state = _uiState.asStateFlow()


}