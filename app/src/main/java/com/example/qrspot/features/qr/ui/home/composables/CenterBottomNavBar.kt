package com.example.qrspot.features.qr.ui.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500

class BottomBarWithCutoutShape(
    private val cutoutRadius: Dp = 36.dp,
    private val cutoutVerticalOffset: Dp = 0.dp,
    private val cornerRadius: Dp = 16.dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path()
        with(density) {
            val radius = cutoutRadius.toPx()
            val corner = cornerRadius.toPx()
            val offsetY = cutoutVerticalOffset.toPx()
            val centerX = size.width / 2f
            val topY = 0f

            // Start at top-left corner (after rounding)
            path.moveTo(0f, topY + corner)
            path.quadraticBezierTo(0f, topY, corner, topY)

            // Line to start of cutout
            path.lineTo(centerX - radius, topY)

            // Semicircular cutout
            val arcRect = Rect(
                left = centerX - radius,
                top = topY - radius - offsetY,
                right = centerX + radius,
                bottom = topY + radius - offsetY
            )
            path.arcTo(
                rect = arcRect,
                startAngleDegrees = 180f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )

            // Continue to top-right corner with rounded edge
            path.lineTo(size.width - corner, topY)
            path.quadraticBezierTo(size.width, topY, size.width, topY + corner)

            // Bottom-right corner
            path.lineTo(size.width, size.height - corner)
            path.quadraticBezierTo(
                size.width,
                size.height,
                size.width - corner,
                size.height
            )

            // Bottom-left corner
            path.lineTo(corner, size.height)
            path.quadraticBezierTo(0f, size.height, 0f, size.height - corner)

            path.close()
        }
        return Outline.Generic(path)
    }
}


@Composable
fun BottomAppBarWithDockedCentreButton(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth(),
    ) {


        Card(
            colors = CardDefaults.cardColors(
                containerColor = darkGrey500
            ),
            shape = BottomBarWithCutoutShape(
                cornerRadius = 25.dp,
                cutoutRadius = 40.dp
            )
            ,
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.generate_qr_icon),
                            tint = Color.Unspecified,
                            contentDescription = "scan button",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text(
                            text = "Generate",
                            fontSize = 14.sp,
                            color = Color.White
                        )

                    }
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.history_icon),
                            tint = Color.Unspecified,
                            contentDescription = "scan button",
                            modifier = Modifier
                                .size(30.dp)

                        )
                        Text(
                            text = "History",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .offset(y = (-35).dp)
                .background(
                    color = yellow500,
                    shape = CircleShape
                )
                .shadow(
                    elevation = 10.dp,
                    spotColor = yellow500
                )
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.scan_icon),
                tint = Color.Unspecified,
                contentDescription = "scan button",
                modifier = Modifier
                    .padding(15.dp)
            )
        }
    }

}

@Preview
@Composable
private fun BottomAppBarWithDockedCentreButtonPreview() {
    BottomAppBarWithDockedCentreButton()
}
