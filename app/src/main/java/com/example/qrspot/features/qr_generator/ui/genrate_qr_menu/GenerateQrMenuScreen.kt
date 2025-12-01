package com.example.qrspot.features.qr_generator.ui.genrate_qr_menu

import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.features.qr_generator.ui.genrate_qr_menu.composables.QrGeneratorMenuItemCard
import com.example.qrspot.features.qr_generator.ui.genrate_qr_menu.models.GenerateQrMenuItem
import com.example.qrspot.ui.theme.darkGrey300
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GenerateQrMenuScreen(
    onNavigateToSettings: () -> Unit,
    onMenuItemClicked: (String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    val menus = listOf<GenerateQrMenuItem>(
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.text_icon),
            text = "Scan"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.www_icon),
            text = "Website"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.wifi_icon),
            text = "Wi-Fi"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.calendar_icon),
            text = "Event"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.contact_icon),
            text = "Contact"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.office_icon),
            text = "Business"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.map_icon),
            text = "Location"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.whatsapp_icon),
            text = "WhatsApp"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.mail_icon),
            text = "Email"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.twiter_icon),
            text = "twitter"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.insta_icon),
            text = "Instagram"
        ),
        GenerateQrMenuItem(
            icon = ImageVector.vectorResource(R.drawable.phone_icon),
            text = "Telephone"
        ),
    )
    Scaffold(
        containerColor = darkGrey300
    ) { innerPadding ->
        Column(
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

                Text(
                    text = "Generate QR",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                )
                Spacer(Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .background(
                            color = darkGrey500,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable(onClick = onNavigateToSettings)
                        .padding(3.dp)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.hamburger_menu_icon),
                        contentDescription = "Back",
                        tint = yellow500
                    )
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(60.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(menus) {menu ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                    ) {
                        QrGeneratorMenuItemCard(
                            icon = menu.icon,
                            text = menu.text,
                            onMenuItemClicked = {
                                onMenuItemClicked(menu.text)
                            },
                            sharedTransitionScope = sharedTransitionScope,
                            animatedVisibilityScope = animatedVisibilityScope,
                            modifier = Modifier
                                .padding(10.dp)

                        )
                    }
                }
            }
        }
    }
}




@Preview
@Composable
private fun GenerateQrScreenPreview() {
//    GenerateQrScreen(
//        onNavigateToSettings = {},
//        sharedTransitionScope = rememberSharedTransitionScope(),
//        animatedVisibilityScope = rememberAnimatedVisibilityScope()
//    )
}