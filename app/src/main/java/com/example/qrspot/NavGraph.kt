package com.example.qrspot

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qrspot.features.qr.ui.home.HomeScreen
import com.example.qrspot.features.qr.ui.home.HomeScreenViewModel
import com.example.qrspot.features.qr.ui.splash.SplashScreen
import com.example.qrspot.features.qr.ui.splash.WelcomeScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onNavigateToOnBoarding = {
                    navController.navigate(Welcome)
                }
            )
        }
        composable<Welcome> {
            WelcomeScreen(
                onNavigateToHome = {
                    navController.navigate(Home)
                }
            )
        }
        composable<Home> {
            val viewModel : HomeScreenViewModel = koinViewModel()
            HomeScreen(viewModel)
        }
    }
}


@Serializable
object Splash
@Serializable
object Welcome
@Serializable
object Home