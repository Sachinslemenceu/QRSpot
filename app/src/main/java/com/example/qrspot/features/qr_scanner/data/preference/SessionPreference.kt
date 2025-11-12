package com.example.qrspot.features.qr_scanner.data.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object SessionPreference {
    val Context.dataStore by preferencesDataStore("session")

    val KEY = stringPreferencesKey("SESSION_KEY")

    suspend fun saveSession(value: String, context: Context) {
        context.dataStore.edit {
            it[KEY] = value
        }
    }

    fun getSession(context: Context): Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[KEY]?: "NO_SESSION" }
}