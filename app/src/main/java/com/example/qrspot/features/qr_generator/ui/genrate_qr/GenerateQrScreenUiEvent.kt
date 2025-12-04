package com.example.qrspot.features.qr_generator.ui.genrate_qr

import kotlinx.serialization.Serializable

@Serializable
sealed class GenerateQrScreenUiEvent {
@Serializable
    class GenerateQrCodeFromText(val text: String): GenerateQrScreenUiEvent()
    @Serializable
    class GenerateQrCodeFromWifiForm(val network: String, val password: String): GenerateQrScreenUiEvent()
    @Serializable
    class GenerateQrCodeFromEventFormContactForm(
        val eventName: String,
        val startDateTime: String,
        val endDateTime: String,
        val eventLocation: String,
        val description: String,
    ): GenerateQrScreenUiEvent()
    @Serializable
    class GenerateQrCodeFromBusinessForm(
        val companyName: String,
        val industry: String,
        val phoneNumber: String,
        val email: String,
        val website: String,
        val address: String,
        val city: String,
        val country: String,
    ): GenerateQrScreenUiEvent()
    @Serializable
    class GenerateQrCodeFromContactForm(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
        val email: String,
        val address: String,
        val company: String,
        val job: String,
        val website: String,
        val city: String,
        val country: String,
    ): GenerateQrScreenUiEvent()

    object OnBackClick: GenerateQrScreenUiEvent()
}

