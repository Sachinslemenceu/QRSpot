package com.example.qrspot.features.qr_scanner.ui.settings

import com.example.qrspot.features.qr_scanner.ui.settings.models.SettingsType

sealed class SettingsUiEvent {
    data class OnSettingsToggled(val isEnabled: Boolean, val type: SettingsType): SettingsUiEvent()
}