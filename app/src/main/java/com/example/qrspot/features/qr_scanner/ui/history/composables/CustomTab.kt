package com.example.qrspot.features.qr_scanner.ui.history.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500

@Composable
fun CustomTab(
    tabItems: List<String>,
    selectedIndex: Int,
    onClick:(Int) -> Unit,
    modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = darkGrey500
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            tabItems.forEachIndexed { index, string ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = if (index == selectedIndex) yellow500 else Color.Transparent,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable(
                            onClick = {
                                onClick(index)
                            }
                        )
                ) {
                    Text(
                        text = string,
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
            }

        }
    }
}