package com.example.qrspot.features.qr_generator.ui.genrate_qr.models

import androidx.compose.ui.graphics.vector.ImageVector

data class GenerateQrDetailItem(
    val icon: ImageVector,
    val fieldTitle: String? = null,
    val fieldLabel: String? = null,
    val isWifiMenu: Boolean = false,
    val isEventMenu: Boolean = false,
    val isContactMenu: Boolean = false,
    val isBusinessMenu: Boolean = false,
)
