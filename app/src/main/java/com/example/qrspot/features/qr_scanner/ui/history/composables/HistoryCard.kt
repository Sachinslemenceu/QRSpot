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
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.features.qr_scanner.domain.models.QrCode
import com.example.qrspot.features.qr_scanner.ui.history.ui_models.HistoryUiModel
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.lightGrey300

@Composable
fun HistoryCard(
    scannedText: String,
    historyType: String,
    scannedTime: String,
    onDeleteClicked:() -> Unit,
    onLinkClicked: () -> Unit = {},
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
            Column(
                modifier = Modifier
                    .clickable(onClick = onLinkClicked)
                    .weight(1f)
            ) {
                BasicText(
                    text = scannedText,
                    modifier = Modifier.fillMaxWidth(),
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 11.sp,
                        maxFontSize = 17.sp,
                        stepSize = 1.sp
                    ),
                    maxLines = 2,
                    style = TextStyle(color = lightGrey300)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = historyType,
                    fontSize = 11.sp,
                    color = Color(0xFFA4A4A4)
                )
            }
            Spacer(Modifier.width(10.dp))
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
                    fontSize = 7.sp,
                    color = Color(0xFFA4A4A4)
                )
            }

        }
    }

}


@Preview
@Composable
private fun HistoryCardPreview() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HistoryCard(
            scannedText = "https://itunes.com",
            historyType = "link",
            scannedTime = "16 Dec 2022, 9:30 pm",
            onDeleteClicked = {}
        )
        HistoryCard(
            scannedText = "https://itunes.comhttps://itunes.comhttps://itunes.comhttps://itunes.comhttps://itunes.comtps://itunes.comhttps://itunes.comhttps://itunes.com",
            historyType = "link",
            scannedTime = "16 Dec 2022, 9:30 pm",
            onDeleteClicked = {}
        )

    }
}