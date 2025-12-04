package com.example.qrspot.features.qr_generator.ui.genrate_qr.composables

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.features.qr.ui.result.composables.ResultButton
import com.example.qrspot.features.qr_generator.utils.createPngFileUri
import com.example.qrspot.features.qr_generator.utils.imageBitmapToPngByteArray
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500
import com.lightspark.composeqr.QrCodeView
import dev.shreyaspatil.capturable.capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowQrSection(
    qrCode: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val captureController = rememberCaptureController()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val saveLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("image/png"),
    ) { uri ->
        uri?.let { destinationUri ->
            scope.launch {
                // You will need a way to get the bitmap bytes here
                // Let's re-capture for simplicity, but ideally you pass it.
                val bitmap = captureController.captureAsync().await()
                val bytes = imageBitmapToPngByteArray(bitmap)

                try {
                    context.contentResolver.openOutputStream(destinationUri)?.use { outputStream ->
                        outputStream.write(bytes)
                    }
                    Toast.makeText(context, "File Saved", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(context, "Failed to save file", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }

    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .background(
                        color = darkGrey500,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable(onClick = onBackClick)
                    .padding(3.dp)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = yellow500
                )
            }
            Text(
                text = "Show QR",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
            )
        }

        Spacer(Modifier.weight(1f))
        QrCodeView(
            data = qrCode,
            modifier = Modifier
                .padding(20.dp)
                .size(181.dp)
                .border(
                    width = 1.5.dp,
                    color = yellow500
                )
                .capturable(
                    captureController
                )
        )
        Spacer(Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            ResultButton(
                text = "Share",
                icon = ImageVector.vectorResource(R.drawable.share_icon),
                onClick = {
                    Toast.makeText(context, "Share Clicked", Toast.LENGTH_SHORT).show()
                    scope.launch {
                        val bitmapAsync = captureController.captureAsync()
                        try {
                            val bitmap = bitmapAsync.await()
                            val pngBytes = imageBitmapToPngByteArray(bitmap)
                            Log.d("ShowQrSection", "PNG Bytes Size: ${pngBytes.size}")
                            val uri = createPngFileUri(context, pngBytes)
                            Log.d("ShowQrSection", "QR Code URI: $uri")
                            if (uri != null) {
                                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "image/*"
                                    putExtra(Intent.EXTRA_STREAM, uri)
                                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                }
                                context.startActivity(
                                    Intent.createChooser(shareIntent, "Share QR Code")
                                )
                            } else{
                                Log.e("ShowQrSection", "Failed to create PNG file URI")
                            }
                        } catch (e: Exception) {
                            Log.e("ShowQrSection", "Error capturing QR code", e)
                            e.printStackTrace()
                        }
                    }
                }
            )
            Spacer(Modifier.width(20.dp))
            ResultButton(
                text = "Save",
                icon = ImageVector.vectorResource(R.drawable.save_icon),
                onClick = {
                    Toast.makeText(context, "Save Clicked", Toast.LENGTH_SHORT).show()
                    scope.launch {
                        saveLauncher.launch("my_saved_qr.png")
                    }
                }
            )
        }
        Spacer(Modifier.weight(1f))

    }

}