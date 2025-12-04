package com.example.qrspot.features.qr_generator.ui.genrate_qr

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GenerateQrViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(GenerateQrScreenUiState())
    val uiState = _uiState.asStateFlow()


    fun onEvent(event: GenerateQrScreenUiEvent){
        when(event){
            is GenerateQrScreenUiEvent.GenerateQrCodeFromBusinessForm -> {
               createJsonStringFromObject(event)
            }
            is GenerateQrScreenUiEvent.GenerateQrCodeFromContactForm -> {
                createJsonStringFromObject(event)
            }
            is GenerateQrScreenUiEvent.GenerateQrCodeFromEventFormContactForm -> {
           createJsonStringFromObject(event)
            }
            is GenerateQrScreenUiEvent.GenerateQrCodeFromText -> {
                Log.d("GenerateQrViewModel", "GenerateQrCodeFromText: $event")
                createJsonStringFromObject(event)

            }
            is GenerateQrScreenUiEvent.GenerateQrCodeFromWifiForm -> {
                createJsonStringFromObject(event)
            }
            GenerateQrScreenUiEvent.OnBackClick -> {
                _uiState.value = _uiState.value.copy(
                    qrCode = null
                )
            }
        }
    }


    private fun createJsonStringFromObject(kotlinObject: GenerateQrScreenUiEvent){
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        val jsonString = Json.encodeToString(kotlinObject)
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            qrCode = jsonString
        )
    }


}