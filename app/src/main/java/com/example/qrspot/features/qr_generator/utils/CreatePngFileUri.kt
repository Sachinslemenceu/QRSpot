package com.example.qrspot.features.qr_generator.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

fun createPngFileUri(context: Context, pngBytes: ByteArray): Uri? {
    return try {
        val imagePath = File(context.cacheDir, "images")
        imagePath.mkdirs()
        val file = File(imagePath, "qr_code_to_share.png")
        val outputStream = FileOutputStream(file)
        outputStream.write(pngBytes)
        outputStream.close()
        val authority = "${context.packageName}.provider"
        FileProvider.getUriForFile(context, authority, file)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}