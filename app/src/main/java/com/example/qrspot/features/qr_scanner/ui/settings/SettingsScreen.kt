package com.example.qrspot.features.qr_scanner.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.model.content.RectangleShape
import com.example.qrspot.R
import com.example.qrspot.features.qr_scanner.ui.settings.composables.SettingCard
import com.example.qrspot.ui.theme.darkGrey300
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isVibrationEnabled by remember { mutableStateOf(false) }
    var isBeepEnabled by remember { mutableStateOf(false) }
    Scaffold(
        containerColor = darkGrey300
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxWidth()
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
                    .align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = yellow500
                )
            }
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Settings",
                fontSize = 26.sp,
                color = yellow500,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(20.dp))
            SettingCard(
                settingName = "Vibrate",
                settingDescription = "Vibration when scan is done.",
                isToggled = isVibrationEnabled,
                icon = ImageVector.vectorResource(R.drawable.vibrate_icon),
                onClick = {
                    isVibrationEnabled = it
                }
            )
            Spacer(Modifier.height(15.dp))
            SettingCard(
                settingName = "Beep",
                settingDescription = "Beep when scan is done.",
                isToggled = isBeepEnabled,
                icon = ImageVector.vectorResource(R.drawable.beep_icon),
                onClick = {
                    isBeepEnabled = it
                }
            )
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Support",
                fontSize = 26.sp,
                color = yellow500,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(20.dp))
            SettingCard(
                settingName = "Rate Us",
                settingDescription = "Your best reward to us.",
                icon = ImageVector.vectorResource(R.drawable.tick_icon),
                onClick = {
                },
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp
                )
            )
            Spacer(Modifier.height(2.dp))
            SettingCard(
                settingName = "Share",
                settingDescription = "Share app with others.",
                icon = ImageVector.vectorResource(R.drawable.share_icon),
                onClick = {
                },
                shape = RectangleShape
            )
            Spacer(Modifier.height(2.dp))
            SettingCard(
                settingName = "Privacy Policy",
                settingDescription = "Follow our policies that benefits you.",
                icon = ImageVector.vectorResource(R.drawable.privacy_icon),
                onClick = {
                },
                shape = RoundedCornerShape(
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
        }
    }
}


@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(
        onBackClick = {}
    )
}