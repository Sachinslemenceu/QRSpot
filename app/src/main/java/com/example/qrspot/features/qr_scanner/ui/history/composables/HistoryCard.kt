package com.example.qrspot.features.qr_scanner.ui.history.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.lightGrey300

@Composable
fun HistoryCard(
    scannedText: String,
    historyType: String,
    scannedTime: String,
    onDeleteClicked:() -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = darkGrey500
        ),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 20.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.splash_icon),
                contentDescription = "scan icon",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(33.dp)
            )
            Spacer(Modifier.width(15.dp))
            Column() {
                Text(
                    text = scannedText,
                    fontSize = 17.sp,
                    color = lightGrey300
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = historyType,
                    fontSize = 11.sp,
                    color = Color(0xFFA4A4A4)
                )
            }
            Spacer(Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.delete_icon),
                    contentDescription = "scan icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = onDeleteClicked
                        )
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = scannedTime,
                    fontSize = 11.sp,
                    color = Color(0xFFA4A4A4)
                )
            }

        }
    }

}


@Preview
@Composable
private fun HistoryCardPreview() {
    HistoryCard(
        scannedText = "https://itunes.com",
        historyType = "link",
        scannedTime = "16 Dec 2022, 9:30 pm",
        onDeleteClicked = {}
    )
}