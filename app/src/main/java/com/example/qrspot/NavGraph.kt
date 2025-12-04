package com.example.qrspot

import android.content.Context
import android.media.SoundPool
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.qrspot.core.database.models.SettingsEntity
import com.example.qrspot.features.qr_generator.ui.genrate_qr.GenerateQrScreen
import com.example.qrspot.features.qr_generator.ui.genrate_qr.GenerateQrViewModel
import com.example.qrspot.features.qr_generator.ui.genrate_qr_menu.GenerateQrMenuScreen
import com.example.qrspot.features.qr_generator.ui.genrate_qr_menu.models.GenerateQrMenuItem
import com.example.qrspot.features.qr_scanner.ui.history.HistoryScreen
import com.example.qrspot.features.qr_scanner.ui.history.HistoryViewModel
import com.example.qrspot.features.qr_scanner.ui.home.HomeScreen
import com.example.qrspot.features.qr_scanner.ui.home.HomeScreenViewModel
import com.example.qrspot.features.qr_scanner.ui.home.composables.CustomBottomNavBar
import com.example.qrspot.features.qr_scanner.ui.result.ResultScreen
import com.example.qrspot.features.qr_scanner.ui.settings.SettingsScreen
import com.example.qrspot.features.qr_scanner.ui.settings.SettingsViewModel
import com.example.qrspot.features.qr_scanner.ui.splash.SplashScreen
import com.example.qrspot.features.qr_scanner.ui.splash.WelcomeScreen
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val realm = MyApp.realm
    val bottomNavRoutes = listOf(
        "com.example.qrspot.Home",
        "com.example.qrspot.History",
        "com.example.qrspot.GenerateQrMenu"
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedItem by remember { mutableStateOf(2) }

    LaunchedEffect(currentRoute) {
        selectedItem = when (currentRoute) {
            "com.example.qrspot.Home" -> 2
            "com.example.qrspot.History" -> 1
            "com.example.qrspot.GenerateQr" -> 0
            else -> 2
        }
    }

    val soundPool = SoundPool.Builder()
        .setMaxStreams(1)
        .build()
    val soundId = soundPool.load(
        context,
        R.raw.pop_sound,
        1
    )
    val settingsFlow = realm
        .query<SettingsEntity>(
            "id == $0",
            "APP_SETTINGS"
        )
        .asFlow()
        .map { resultsChange ->
            resultsChange.list.firstOrNull()
        }
    val currentSettings by settingsFlow.collectAsState(initial = null)

    val isVibrationEnabled = currentSettings?.vibrate ?: false
    val isBeepEnabled = currentSettings?.beep ?: false

    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavRoutes) {
                Log.d("NavGraph", "Bottom Nav Bar Shown")
                CustomBottomNavBar(
                    selectedItem = selectedItem,
                    onGenerateClicked = {
//                        selectedItem = 0
                        if (currentRoute != "com.example.qrspot.GenerateQrMenu") {
                            navController.navigate(GenerateQrMenu)
                        }
                    },
                    onHistoryClicked = {
//                        selectedItem = 1
                        if (currentRoute != "com.example.qrspot.History") {
                            navController.navigate(History)
                        }
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
//                        selectedItem = 2
                        if (currentRoute != "com.example.qrspot.Home") {
                            navController.navigate(Home)
                        }
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
        SharedTransitionLayout {
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
                            Log.d(
                                "NavGraphLogs",
                                "IsVibrationEnabled: $isVibrationEnabled isBeepEnabled: $isBeepEnabled"
                            )
                            if (isBeepEnabled) {
                                soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
                            }
                            if (isVibrationEnabled) {
                                vibrator.vibrate(
                                    VibrationEffect.createOneShot(
                                        500,
                                        VibrationEffect.DEFAULT_AMPLITUDE
                                    )
                                )
                            }
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
                composable<History>(
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    }
                ) {
                    val viewModel: HistoryViewModel = koinViewModel()
                    HistoryScreen(
                        uiState = viewModel.uiState.collectAsState().value,
                        onEvent = viewModel::onEvent,
                        onViewLink = { scannedText, category, time ->
                            navController.navigate(Result(scannedText, category, time))
                        },
                        onNavigateToSettings = {
                            navController.navigate(Settings)
                        }
                    )
                }
                composable<Settings>(
                    enterTransition = {
                        scaleIn(
                            animationSpec = tween(500)
                        )
                    },
                    exitTransition = {
                        scaleOut(
                            animationSpec = tween(500)
                        )
                    }
                ) {
                    val viewModel: SettingsViewModel = koinViewModel()
                    SettingsScreen(
                        uiState = viewModel.state.collectAsState().value,
                        onEvent = viewModel::onEvent,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
                composable<GenerateQrMenu>(
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    }
                ) {
                    GenerateQrMenuScreen(
                        onNavigateToSettings = {
                            navController.navigate(Settings)
                        },
                        onMenuItemClicked = {menu ->
                            navController.navigate(GenerateQr(menu))
                        },
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this,
                        modifier = Modifier
                    )
                }
                composable<GenerateQr>(
//                    enterTransition = {
//                        slideIntoContainer(
//                            AnimatedContentTransitionScope.SlideDirection.Right,
//                            animationSpec = tween(700)
//                        )
//                    },
//                    exitTransition = {
//                        slideOutOfContainer(
//                            AnimatedContentTransitionScope.SlideDirection.Left,
//                            animationSpec = tween(700)
//                        )
//                    }
                ) {
                    val viewModel: GenerateQrViewModel = koinViewModel()
                    val menuName = it.arguments?.getString("menuName")
                    menuName?.let {
                        GenerateQrScreen(
                            onBackClick = {
                                navController.popBackStack()
                            },
                            menuName = menuName,
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedVisibilityScope = this,
                            uiState = viewModel.uiState.collectAsState().value,
                            onEvent = viewModel::onEvent,
                            modifier = Modifier
                        )

                    }
                }
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
object GenerateQrMenu
@Serializable
class GenerateQr(val menuName: String)

@Serializable
class Result(val barcode: String, val category: String = "Scanned", val time: String)