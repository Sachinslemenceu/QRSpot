package com.example.qrspot.features.qr.ui.home

import android.media.Image
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.features.qr.ui.home.composables.BottomAppBarWithDockedCentreButton
import com.example.qrspot.features.qr.ui.home.composables.BottomBarWithCutoutShape
import com.example.qrspot.features.qr.ui.home.composables.CameraPreviewContent
import com.example.qrspot.features.qr.ui.home.composables.CustomBottomNavBar
import com.example.qrspot.features.qr.ui.home.HomeScreenViewModel
import com.example.qrspot.features.qr.ui.home.composables.TopBarComponent
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500
import com.example.qrspot.ui.theme.yellow900
import com.example.qrspot.util.permissions.PermissionManager

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier
) {
    var hasPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ){isGranted ->
        hasPermission = true
    }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (!PermissionManager.checkIsCameraPermissionGranted(context)){
            permissionLauncher.launch(android.Manifest.permission.CAMERA)
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
            viewModel
        )
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            TopBarComponent(
                onOpenGallery = {},
                onTurnOnFlash = {},
                modifier = Modifier
                    .padding(horizontal = 120.dp, vertical = 15.dp)
            )
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
//    HomeScreen()
}

