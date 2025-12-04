package com.example.qrspot.features.qr_generator.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import java.io.ByteArrayOutputStream


fun imageBitmapToPngByteArray(imageBitmap: ImageBitmap): ByteArray {
    val bitmap = imageBitmap.asAndroidBitmap()
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream)
    return outputStream.toByteArray()
}

