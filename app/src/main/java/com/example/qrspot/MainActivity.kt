package com.example.qrspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.example.qrspot.ui.theme.QRSpotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QRSpotTheme {
                NavGraph(
                    modifier = Modifier
                )
//                SplashScreen(
//                    onNavigateToOnBoarding = {}
//                )
            }
        }
    }
}

