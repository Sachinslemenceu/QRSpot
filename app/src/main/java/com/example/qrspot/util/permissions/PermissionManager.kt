package com.example.qrspot.util.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionManager {

    fun checkIsCameraPermissionGranted(
        context: Context
    ): Boolean{
        return ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }
}