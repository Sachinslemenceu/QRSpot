package com.example.qrspot.features.qr_generator.ui.genrate_qr_menu.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun QrGeneratorMenuItemCard(
    icon: ImageVector,
    text: String,
    onMenuItemClicked: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {


    Card(
        colors = CardDefaults.cardColors(
            containerColor = darkGrey500
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onMenuItemClicked
            )
    ) {
        with(sharedTransitionScope) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "scan icon",
                    tint = yellow500,
                    modifier = Modifier
                        .size(35.dp)
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "$text-icon"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(500)
                            }
                        )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = text,
                    fontSize = 12.sp,
                    color = yellow500,
                    modifier = Modifier
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = text),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(500)
                            }
                        )
                )
            }
        }
    }
}


@Preview
@Composable
private fun QrGeneratorMenuItemPreview() {
//    QrGeneratorMenuItemCard(
//        icon = ImageVector.vectorResource(R.drawable.text_icon),
//        text = "Scan",
//        onMenuItemClicked = {}
//    )
}