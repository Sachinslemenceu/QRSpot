package com.example.qrspot.features.qr_scanner.ui.home

import android.Manifest
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.qrspot.R
import com.example.qrspot.features.qr.ui.home.composables.CameraPreviewContent
import com.example.qrspot.features.qr_scanner.ui.home.composables.CustomBottomNavBar
import com.example.qrspot.features.qr_scanner.ui.home.composables.TopBarComponent
import com.example.qrspot.ui.theme.yellow500
import com.example.qrspot.util.permissions.PermissionManager
import com.google.mlkit.vision.common.InputImage
import java.net.URI

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
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { uri ->
        selectedImageUri = uri
        selectedImageUri?.let { imageUri ->
            val inputImage = InputImage.fromFilePath(context, imageUri)
            viewModel.extractQrCodeFromImage(
                inputImage,
                onQrCodeScanned = {
                    if (it == "") {
                        Toast.makeText(context, "No QR Code Found", Toast.LENGTH_SHORT).show()
                    } else {
                        onQrCodeScanned(it)
                    }
                }
            )
        }
        Log.d("HomeScreen", "Selected image URI: $selectedImageUri")
    }

    LaunchedEffect(Unit) {
        if (!PermissionManager.checkIsCameraPermissionGranted(context)) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    Scaffold(
        bottomBar = {

            CustomBottomNavBar(
                onGenerateClicked = {},
                onHistoryClicked = {}
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = yellow500,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 10.dp
                ),
                onClick = {},
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
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        CameraPreviewContent(
            viewModel,
            onQrCodeScanned = onQrCodeScanned
        )
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            TopBarComponent(
                onOpenGallery = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                onTurnOnFlash = {},
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
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
//    HomeScreen()
}

