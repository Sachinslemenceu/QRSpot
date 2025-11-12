package com.example.qrspot.features.qr_scanner.ui.splash

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.features.qr_scanner.data.preference.SessionPreference
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = darkGrey500,
        floatingActionButton = {
            ElevatedButton(
                onClick = {
                    scope.launch {
                        SessionPreference.saveSession("IN_SESSION", context = context)
                        onNavigateToHome()
                    }
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = yellow500,
                ),
                shape = CircleShape,
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 20.dp
                ),
                modifier = Modifier
                    .size(70.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = CircleShape,
                        spotColor = yellow500
                    )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "next button",
                    tint = darkGrey500,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .background(color = yellow500)
        ) {
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.welcome_icon),
                contentDescription = "welcome icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Get Started",
                color = darkGrey500,
                fontSize = 42.sp
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Go and enjoy our features for free and make your life easy with us.",
                color = darkGrey500.copy(0.6f),
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )
            Spacer(Modifier.weight(0.5f))
            Canvas(
                modifier = Modifier
                    .padding(start = 30.dp)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                val width = size.width
                val height = size.height

                val path = Path().apply {
                    moveTo(0f, height * 1f)

                    // Draw a smooth cubic curve
                    cubicTo(
                        width * 0.25f, height * 0.45f,  // first control point
                        width * 0.75f, height * 0.55f,  // second control point
                        width, height * 0.5f             // end point
                    )

                    // Close the shape by drawing lines to bottom corners
                    lineTo(width, height)
                    lineTo(0f, height)
                    close()
                }

                drawPath(
                    path = path,
                    color = darkGrey500 // any color you like
                )
            }
        }

    }
}


@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(
        onNavigateToHome = {}
    )
}