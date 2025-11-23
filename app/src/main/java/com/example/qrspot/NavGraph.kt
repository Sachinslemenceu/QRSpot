package com.example.qrspot

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(
                onNavigateToOnBoarding = {
                    navController.navigate(Welcome){
                        popUpTo(Splash){
                            inclusive = true
                        }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(Home){
                        popUpTo(Splash){
                            inclusive = true
                        }
                    }
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
        composable<Home>(
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            val viewModel: HomeScreenViewModel = koinViewModel()
            HomeScreen(
                viewModel,
                onQrCodeScanned = {
                    val time = LocalDateTime.now()
                    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
                    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                    val formattedTime = time.format(timeFormatter)
                    val formattedDate = time.format(dateFormatter)
                    val timeString = "$formattedDate ,$formattedTime"
                    navController.navigate(Result(it, time = timeString))
                },
                onHistoryClicked = {
                    navController.navigate(History)
                },
                onGenerateClicked = {
                    Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                },
            )
        }
        composable<Result>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            val barcode = it.arguments?.getString("barcode")
            val time = it.arguments?.getString("time")
            val category = it.arguments?.getString("category")
            barcode?.let {
                ResultScreen(
                    scannedText = barcode,
                    onBackClick = {
                        Log.d("NavGraph", "Back Clicked")
                        navController.popBackStack()
                    },
                    category = category!!,
                    time = time!!
                )
            }
        }
        composable<History> {
            val viewModel: HistoryViewModel = koinViewModel()
            HistoryScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onEvent = viewModel::onEvent,
                onViewLink = {scannedText, category, time->
                    navController.navigate(Result(scannedText,category,time))
                }
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
class Result(val barcode: String, val category: String = "Scanned", val time: String)