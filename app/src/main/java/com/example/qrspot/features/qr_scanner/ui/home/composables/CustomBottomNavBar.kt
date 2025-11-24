package com.example.qrspot.features.qr_scanner.ui.home.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500
import com.example.qrspot.ui.theme.yellow900
@Composable
fun CustomBottomNavBar(
    onGenerateClicked: () -> Unit,
    onHistoryClicked: () -> Unit,
    selectedItem: Int = 0,
    modifier: Modifier = Modifier
) {

    BottomAppBar(
        containerColor = Color.Transparent,
        modifier = modifier
            .padding(15.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = BottomBarWithCutoutShape(
                cornerRadius = 25.dp,
                cutoutRadius = 40.dp
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            )
        ) {
            NavigationBar(containerColor = darkGrey500) {
                NavigationBarItem(
                    onClick = {
                        onGenerateClicked()
                    },
                    selected = selectedItem == 0,
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.generate_qr_icon),
                            contentDescription = "Generate",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text("Generate", fontSize = 14.sp)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = yellow900,
                        selectedTextColor = yellow900,
                        indicatorColor = yellow500,
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )

                NavigationBarItem(
                    onClick = {
                        onHistoryClicked()
                    },
                    selected = selectedItem == 1,
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.history_icon),
                            contentDescription = "History",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text("History", fontSize = 14.sp)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = yellow900,
                        selectedTextColor = yellow900,
                        indicatorColor = yellow500,
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White
                    )
                )
            }
        }
    }
}
