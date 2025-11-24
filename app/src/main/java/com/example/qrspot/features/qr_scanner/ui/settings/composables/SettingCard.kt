package com.example.qrspot.features.qr_scanner.ui.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.lightGrey300
import com.example.qrspot.ui.theme.yellow500

@Composable
fun SettingCard(
    icon: ImageVector,
    settingName: String,
    settingDescription: String,
    isToggled: Boolean? = null,
    onClick: (Boolean) -> Unit = {},
    shape: Shape = RoundedCornerShape(10.dp),
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = darkGrey500
        ),
        shape = shape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = modifier
            .clickable(
                onClick = {
                    if (isToggled == null) {
                        onClick(true)
                    }
                }
            )
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = yellow500,
                modifier = Modifier
                    .size(33.dp)
            )
            Spacer(Modifier.width(15.dp))
            Column(
                modifier = Modifier
//                    .clickable(onClick = onLinkClicked)
                    .weight(1f)
            ) {
               Text(
                   text = settingName,
                   fontSize = 16.sp,
                   color = Color.White,
                   fontWeight = FontWeight.SemiBold
               )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = settingDescription,
                    fontSize = 11.sp,
                    color = Color(0xFFA4A4A4)
                )
            }
            if (isToggled !=null){
                Switch(
                    checked = isToggled,
                    onCheckedChange = {
                        onClick(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = yellow500,
                        checkedTrackColor = yellow500.copy(alpha = 0.5f),
                        uncheckedThumbColor = Color(0xFF979797),
                        uncheckedTrackColor = Color(0xFFFFFFFF).copy(alpha = 0.38f)
                    )
                )
            }
        }
    }
}


@Preview
@Composable
private fun SettingCardPreview() {
    SettingCard(
        icon = ImageVector.vectorResource(R.drawable.splash_icon),
        settingName = "Vibrate",
        settingDescription = "Vibrate well when phone on"
    )
}