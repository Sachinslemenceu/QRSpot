package com.example.qrspot.features.qr_scanner.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qrspot.R
import com.example.qrspot.features.qr_scanner.data.preference.SessionPreference
import com.example.qrspot.ui.theme.darkGrey500
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToOnBoarding:() -> Unit,
    onNavigateToHome:() -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val sessionInfo by SessionPreference.getSession(context).collectAsState("NO_SESSION")
    Scaffold(
        containerColor = darkGrey500
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            var animate by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                animate = true
                delay(5000)
                if (sessionInfo == "NO_SESSION"){
                    onNavigateToOnBoarding()
                } else{
                    onNavigateToHome()
                }
            }
            val angle by animateFloatAsState(
                targetValue = if (animate) 360f else 0f,
                animationSpec = tween(1000),
            )
            val scale by animateFloatAsState(
                targetValue = if (animate) 1f else 0.5f,
                animationSpec = tween(2000),
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.splash_icon),
                contentDescription = "Splash Icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(200.dp)
                    .rotate(angle)
                    .scale(scale)
            )
        }
    }
}


@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        onNavigateToOnBoarding = {},
        onNavigateToHome = {}
    )
}