package com.example.qrspot.features.qr_scanner.ui.result

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.lightGrey300
import com.example.qrspot.ui.theme.yellow500
import androidx.core.net.toUri
import com.example.qrspot.features.qr.ui.result.composables.ResultButton
import com.lightspark.composeqr.QrCodeView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    scannedText: String,
    category: String,
    time: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showQrCode by remember { mutableStateOf(false) }
    Scaffold(
        containerColor = darkGrey500.copy(alpha = 0.80f)
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
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
                    text = "Result",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            Spacer(Modifier.weight(0.3f))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = darkGrey500
                ),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.splash_icon),
                            contentDescription = "Scan Icon",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(Modifier.width(15.dp))
                        Column() {
                            Text(
                                text = category,
                                fontSize = 22.sp,
                                color = lightGrey300
                            )
                            Text(
                                text = time,
                                fontSize = 13.sp,
                                color = Color(0xFFA4A4A4)
                            )
                        }
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    Text(
                        text = scannedText,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = if (!showQrCode) "Show QR Code" else "Hide QR Code",
                        fontSize = 13.sp,
                        color = yellow500,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable(onClick = { showQrCode = !showQrCode })
                    )
                    AnimatedVisibility(
                        visible = showQrCode,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        QrCodeView(
                            data = scannedText,
                            modifier = Modifier
                                .padding(20.dp)
                                .size(181.dp)
                                .border(
                                    width = 1.5.dp,
                                    color = yellow500
                                )
                        )
                    }
                }
            }
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
                        try {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_TEXT, scannedText)
                            context.startActivity(Intent.createChooser(intent, "Share via"))
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                )
                Spacer(Modifier.width(20.dp))
                ResultButton(
                    text = "Open",
                    icon = ImageVector.vectorResource(R.drawable.browser_icon),
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW, scannedText.toUri())
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                )
                Spacer(Modifier.width(20.dp))
                ResultButton(
                    text = "Copy",
                    icon = ImageVector.vectorResource(R.drawable.copy_icon),
                    onClick = {
                        try {
                            val clip: ClipData = ClipData.newPlainText("simple text", scannedText)
                            val clipboardManager =
                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboardManager.setPrimaryClip(clip)

                            Toast.makeText(context, "Copied To Clipboard", Toast.LENGTH_SHORT)
                                .show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
private fun ResultScreenPreview() {
    ResultScreen(
        scannedText = "https://www.google.com/",
        category = "Scanned",
        time = "19:0 dec tue",
        onBackClick = {}
    )
}