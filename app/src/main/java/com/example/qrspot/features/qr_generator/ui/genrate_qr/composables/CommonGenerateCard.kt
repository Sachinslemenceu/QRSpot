package com.example.qrspot.features.qr_generator.ui.genrate_qr.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.ui.theme.darkGrey300
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.lightGrey300
import com.example.qrspot.ui.theme.yellow500

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CommonGenerateCard(
    icon: ImageVector,
    fieldTitle: String,
    fieldLabel: String,
    menuName: String,
    onGenerateClicked: (String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    modifier: Modifier = Modifier
) {

    var value by remember { mutableStateOf("") }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = darkGrey500.copy(alpha = 0.7f)
        ),
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 30.dp)
                .fillMaxWidth()
        ) {
            with(sharedTransitionScope){
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = yellow500,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(60.dp)
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "$menuName-icon"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(500)
                            }
                        )
                )
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = fieldTitle,
                fontSize = 14.sp,
                color = Color(0xFFD9D9D9),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = value,
                onValueChange = {value = it},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = darkGrey300,
                    unfocusedBorderColor = darkGrey300,
                    focusedLabelColor = lightGrey300,
                    focusedTextColor = lightGrey300,
                    unfocusedTextColor = lightGrey300,
                    cursorColor = yellow500
                ),
                label = {
                    Text(
                        text = fieldLabel,
                        fontSize = 12.sp
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(40.dp))
            ElevatedButton(
                onClick = {
                    onGenerateClicked(value)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = yellow500
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Generate QR Code",
                    color = darkGrey500,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )
            }
        }

    }
}