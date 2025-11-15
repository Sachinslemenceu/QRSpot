package com.example.qrspot

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory
import com.example.qrspot.features.qr_scanner.ui.history.HistoryScreen
import com.example.qrspot.features.qr_scanner.ui.history.HistoryViewModel
import com.example.qrspot.features.qr_scanner.ui.home.HomeScreen
import com.example.qrspot.features.qr_scanner.ui.home.HomeScreenViewModel
import com.example.qrspot.features.qr_scanner.ui.result.ResultScreen
import com.example.qrspot.features.qr_scanner.ui.splash.SplashScreen
import com.example.qrspot.features.qr_scanner.ui.splash.WelcomeScreen
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
                },
                onNavigateToHome = {
                    navController.navigate(Home)
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
            val viewModel: HomeScreenViewModel = koinViewModel()
            HomeScreen(
                viewModel,
                onQrCodeScanned = {
                    navController.navigate(Result(it))
                },
                onHistoryClicked = {
                    navController.navigate(History)
                },
                onGenerateClicked = {}
            )
        }
        composable<Result> {
            val barcode = it.arguments?.getString("barcode")
            barcode?.let {
                ResultScreen(
                    scannedText = barcode,
                    onBackClick = {
                        Log.d("NavGraph", "Back Clicked")
                        navController.popBackStack()
                    }
                )
            }
        }
        composable<History> {
            val viewModel: HistoryViewModel = koinViewModel()
            viewModel.getHistory(
                QrCodeCategory.Scanned
            )
            HistoryScreen(
                uiState = viewModel.uiState.collectAsState().value
            )
        }
    }
}


@Serializable
object Splash

@Serializable
object Welcome

@Serializable
object Home
@Serializable
object History

@Serializable
class Result(val barcode: String)