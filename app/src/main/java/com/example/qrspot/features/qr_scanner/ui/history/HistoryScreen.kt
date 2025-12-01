package com.example.qrspot.features.qr_scanner.ui.history

import androidx.activity.result.launch
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.qrspot.R
import com.example.qrspot.features.qr_scanner.domain.models.QrCodeCategory
import com.example.qrspot.features.qr_scanner.ui.history.composables.CustomTab
import com.example.qrspot.features.qr_scanner.ui.history.composables.HistoriesCard
import com.example.qrspot.features.qr_scanner.ui.history.composables.HistoryCard
import com.example.qrspot.features.qr_scanner.ui.history.ui_models.HistoryUiModel
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.lightGrey300
import com.example.qrspot.ui.theme.yellow500
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.definition.indexKey

@Composable
fun HistoryScreen(
    uiState: HistoryUiState,
    onViewLink: (String, String, String) -> Unit,
    onNavigateToSettings:() -> Unit = {},
    onEvent: (HistoryUiEvent) -> Unit = {},
    modifier: Modifier = Modifier
) {

    val tabItems = listOf("Scan", "Create")
    var selectedIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { tabItems.size })
    val coroutineScope = rememberCoroutineScope() // Needed to animate tab clicks

    LaunchedEffect(Unit) {
        onEvent(HistoryUiEvent.FetchHistory)
    }
    LaunchedEffect(pagerState.currentPage) {
        val category = if (pagerState.currentPage == 0) {
            QrCodeCategory.Scanned
        } else {
            QrCodeCategory.Generated
        }
        onEvent(HistoryUiEvent.OnFilterHistory(category))
    }

    Scaffold(
        containerColor = darkGrey500.copy(alpha = 0.80f)
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {

                Text(
                    text = "History",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                )
                Spacer(Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .background(
                            color = darkGrey500,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable(onClick = onNavigateToSettings)
                        .padding(3.dp)
                        .size(40.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.hamburger_menu_icon),
                        contentDescription = "Back",
                        tint = yellow500
                    )
                }
            }
            CustomTab(
                tabItems = tabItems,
                selectedIndex = selectedIndex,
                onClick = { index ->
                    selectedIndex = index
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index,
                            animationSpec = tween(1000))
                    }
                }
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                HistoriesCard(
                    uiState = uiState,
                    onEvent = onEvent,
                    onViewLink = onViewLink
                )
            }
        }
    }
}


@Preview
@Composable
private fun HistoryScreenPreview() {
    val uiState = HistoryUiState(
        isLoading = false,
        histories = listOf(
            HistoryUiModel(
                scannedText = "http:/shshhhd",
                scannedTime = "19:0 dec tue",
                type = "link"
            ),
            HistoryUiModel(
                scannedText = "http:/shshhhd",
                scannedTime = "19:0 dec tue",
                type = "link"
            ),
            HistoryUiModel(
                scannedText = "http:/shshhhd",
                scannedTime = "19:0 dec tue",
                type = "link"
            ),
            HistoryUiModel(
                scannedText = "http:/shshhhd",
                scannedTime = "19:0 dec tue",
                type = "link"
            ),
        )
    )
    HistoryScreen(
        uiState = uiState,
        onViewLink = { scannedText, category, time ->

        }
    )
}

@Serializable
object CreateTab

@Serializable
object ScanTab
