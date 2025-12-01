package com.example.qrspot.features.qr_scanner.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrspot.MyApp
import com.example.qrspot.core.database.models.SettingsEntity
import com.example.qrspot.features.qr_scanner.ui.settings.models.SettingsType
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.internal.interop.INDEX_NOT_FOUND
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    val realm = MyApp.realm
    private val _uiState = MutableStateFlow(SettingsUiState())
    val state = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val settings = realm
                .query<SettingsEntity>(
                    "id == $0",
                    "APP_SETTINGS"
                )
                .asFlow()
                .map {
                    it.list.firstOrNull()
                }
            _uiState.value = _uiState.value.copy(
                isVibrationEnabled = settings.first()?.vibrate ?: false,
                isBeepEnabled = settings.first()?.beep ?: false
            )
        }
    }

    fun onEvent(event: SettingsUiEvent) {
        when (event) {
            is SettingsUiEvent.OnSettingsToggled -> {
                when (event.type) {
                    SettingsType.Vibration -> {
                        _uiState.value = _uiState.value.copy(
                            isVibrationEnabled = event.isEnabled
                        )
                        realm.writeBlocking {
                            val settings = this.query<SettingsEntity>(
                                "id == $0",
                                "APP_SETTINGS"
                            ).first().find()
                            if (settings !=null){
                                settings.vibrate = _uiState.value.isVibrationEnabled
                            } else{
                                val setting = SettingsEntity().apply {
                                    vibrate = _uiState.value.isVibrationEnabled
                                }
                                copyToRealm(setting, UpdatePolicy.ALL)
                            }
                        }
                    }

                    SettingsType.Beep -> {
                        _uiState.value = _uiState.value.copy(
                            isBeepEnabled = event.isEnabled
                        )
                        realm.writeBlocking {
                            val settings = this.query<SettingsEntity>(
                                "id == $0",
                                "APP_SETTINGS"
                            ).first().find()
                            if (settings !=null){
                                settings.beep = _uiState.value.isBeepEnabled
                            } else{
                                val setting = SettingsEntity().apply {
                                    beep = _uiState.value.isBeepEnabled
                                }
                                copyToRealm(setting, UpdatePolicy.ALL)
                            }
                        }
                    }
                }
            }
        }
    }


}