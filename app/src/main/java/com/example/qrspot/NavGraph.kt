package com.example.qrspot

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory
import com.example.qrspot.features.qr_scanner.ui.history.HistoryScreen
import com.example.qrspot.features.qr_scanner.ui.history.HistoryViewModel
import com.example.qrspot.features.qr_scanner.ui.home.HomeScreen
import com.example.qrspot.features.qr_scanner.ui.home.HomeScreenViewModel
import com.example.qrspot.features.qr_scanner.ui.home.composables.BottomAppBarWithDockedCentreButton
import com.example.qrspot.features.qr_scanner.ui.home.composables.CustomBottomNavBar
import com.example.qrspot.features.qr_scanner.ui.result.ResultScreen
import com.example.qrspot.features.qr_scanner.ui.settings.SettingsScreen
import com.example.qrspot.features.qr_scanner.ui.splash.SplashScreen
import com.example.qrspot.features.qr_scanner.ui.splash.WelcomeScreen
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current


    val bottomNavRoutes = listOf("com.example.qrspot.Home", "com.example.qrspot.History")
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedItem by remember { mutableStateOf(2) }


    Scaffold(


        bottomBar = {
            if (currentRoute in bottomNavRoutes) {
                CustomBottomNavBar(
                    selectedItem = selectedItem,
                    onGenerateClicked = {
                        Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
                    },
                    onHistoryClicked = {
                        selectedItem = 1
                        navController.navigate(History)
                    }
                )
            }
        },
        floatingActionButton = {
            if (currentRoute in bottomNavRoutes) {

                FloatingActionButton(
                    shape = CircleShape,
                    containerColor = if (selectedItem == 2) yellow500 else darkGrey500,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 10.dp
                    ),
                    onClick = {
                        selectedItem = 2
                        navController.navigate(Home)
                    },
                    modifier = Modifier
                        .offset(y = 70.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.scan_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(20.dp)
                            .size(30.dp)
                    )
                }

            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = Color.Transparent
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Splash,
            modifier = Modifier
        ) {
            composable<Splash>(
                enterTransition = {
                    scaleIn(
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(1000)
                    )
                }
            ) {
                SplashScreen(
                    onNavigateToOnBoarding = {
                        navController.navigate(Welcome) {
                            popUpTo(Splash) {
                                inclusive = true
                            }
                        }
                    },
                    onNavigateToHome = {
                        selectedItem = 2
                        navController.navigate(Home) {
                            popUpTo(Splash) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Welcome>(
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(1000)
                    )
                },
                exitTransition = {
                    //                slideOutOfContainer(
//                towards = AnimatedContentTransitionScope.SlideDirection.Left,
//                animationSpec = tween(1000)
//
//            )+
                    fadeOut(
                        animationSpec = tween(1000)
                    )
                }
            ) {
                WelcomeScreen(
                    onNavigateToHome = {
                        navController.navigate(Home) {
                            popUpTo(Splash) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<Home>(
                enterTransition = {
                    scaleIn(
                        animationSpec = tween(500)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(1000)
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
                    modifier = Modifier
                        .padding(innerPadding)
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
                    onViewLink = { scannedText, category, time ->
                        navController.navigate(Result(scannedText, category, time))
                    }
                )
            }
            composable<Settings> {
                SettingsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
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
object Settings

@Serializable
class Result(val barcode: String, val category: String = "Scanned", val time: String)