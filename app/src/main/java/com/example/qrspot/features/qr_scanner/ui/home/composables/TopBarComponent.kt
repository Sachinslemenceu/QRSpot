package com.example.qrspot.features.qr_scanner.ui.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qrspot.R
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500

@Composable
fun TopBarComponent(
    isFlashOn: Boolean = false,
    onOpenGallery: () -> Unit,
    onTurnOnFlash: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = darkGrey500
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.pic_gallery_icon),
                contentDescription = "Picture gallery icon",
                tint = Color.White,
                modifier = Modifier
                    .clickable(
                        onClick = onOpenGallery
                    )
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.lightning_icon),
                contentDescription = "Lightning Icon",
                tint = if (isFlashOn) yellow500 else Color.White,
                modifier = Modifier
                    .clickable(
                        onClick = onTurnOnFlash
                    )
            )
        }
    }
}


@Preview
@Composable
private fun TopBarComponentPreview() {
    TopBarComponent(
        onOpenGallery = {},
        onTurnOnFlash = {})
}
