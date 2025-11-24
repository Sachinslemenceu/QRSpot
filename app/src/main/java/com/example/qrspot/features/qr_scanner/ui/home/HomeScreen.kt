package com.example.qrspot.features.qr_scanner.ui.home

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.qrspot.R
import com.example.qrspot.features.qr_scanner.ui.home.composables.CameraPreviewContent
import com.example.qrspot.features.qr_scanner.ui.home.composables.CustomBottomNavBar
import com.example.qrspot.features.qr_scanner.ui.home.composables.TopBarComponent
import com.example.qrspot.ui.theme.darkGrey300
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.lightGrey300
import com.example.qrspot.ui.theme.yellow500
import com.example.qrspot.util.permissions.PermissionManager
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onQrCodeScanned: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var hasPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { isGranted ->
        hasPermission = true
    }
    val scope = rememberCoroutineScope()
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var isQrCodeFound by remember { mutableStateOf(false) }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri
        }
    }
    var isReady by remember { mutableStateOf(false) }
    val isFlashOn = viewModel.isFlashOn.collectAsState().value
    LaunchedEffect(selectedImageUri) {
        selectedImageUri?.let { imageUri ->
            val inputImage = InputImage.fromFilePath(context, imageUri)
            viewModel.extractQrCodeFromImage(
                inputImage,
                onQrCodeScanned = {
                    if (it == "") {
                        Toast.makeText(context, "No QR Code Found", Toast.LENGTH_SHORT).show()
                    } else {
                        scope.launch {
                            viewModel.saveQrCode(it)
                            onQrCodeScanned(it)
                        }
                    }
                }
            )
        }

    }

    LaunchedEffect(Unit) {
        if (!PermissionManager.checkIsCameraPermissionGranted(context)) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
            delay(1000)
            isReady = true
        } else {
            delay(1000)
            isReady = true
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
            CameraPreviewContent(
                viewModel,
                onQrCodeScanned = {
                    if (!isQrCodeFound) {
                        isQrCodeFound = true
                        viewModel.saveQrCode(it)
                        onQrCodeScanned(it)

                    }
                }
            )
            Column(
                modifier = modifier
            ) {
                TopBarComponent(
                    onOpenGallery = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    onTurnOnFlash = {
                        viewModel.turnFlashLightOnAndOff()
                    },
                    isFlashOn = isFlashOn,
                    modifier = Modifier
                        .padding(horizontal = 120.dp, vertical = 15.dp)
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.scan_overlay_icon),
                    contentDescription = "Scan Overlay Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
                Spacer(Modifier.weight(1f))
        }
        AnimatedVisibility(
            visible = !isReady,
            modifier = Modifier
                .fillMaxSize()
        ) {

                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.run_animation))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(color = darkGrey300)
                        .fillMaxSize()
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = progress,
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                    )
                    Text(
                        "Setting up Camera for you. please wait...",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }
        }
    }

}


@Preview
@Composable
private fun HomeScreenPreview() {
//    HomeScreen()
}

