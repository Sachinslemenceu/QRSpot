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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.ui.theme.darkGrey300
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.lightGrey300
import com.example.qrspot.ui.theme.yellow500

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EventGenerateCard(
    icon: ImageVector,
    menuName: String,
    onGenerateClicked: (String, String, String, String, String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    modifier: Modifier = Modifier) {
    var eventName by remember { mutableStateOf("") }
    var eventStart by remember { mutableStateOf("") }
    var eventEnd by remember { mutableStateOf("") }
    var eventLocation by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

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
                .padding(20.dp)
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
                text = "Event Name",
                fontSize = 14.sp,
                color = Color(0xFFD9D9D9),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = eventName,
                onValueChange = { eventName = it },
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
                        text = "Enter name",
                        fontSize = 12.sp
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Start Date and Time",
                fontSize = 14.sp,
                color = Color(0xFFD9D9D9),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = eventStart,
                onValueChange = { eventStart = it },
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
                        text = "12 Dec 2022, 10:40 pm",
                        fontSize = 12.sp
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "End Date and Time",
                fontSize = 14.sp,
                color = Color(0xFFD9D9D9),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = eventEnd,
                onValueChange = { eventEnd = it },
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
                        text = "12 Dec 2022, 10:40 pm",
                        fontSize = 12.sp
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Event Location",
                fontSize = 14.sp,
                color = Color(0xFFD9D9D9),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = eventLocation,
                onValueChange = { eventLocation = it },
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
                        text = "Enter location",
                        fontSize = 12.sp
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Description",
                fontSize = 14.sp,
                color = Color(0xFFD9D9D9),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
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
                        text = "Enter any details",
                        fontSize = 12.sp
                    )
                },
                shape = RoundedCornerShape(10.dp),
                maxLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(40.dp))
            ElevatedButton(
                onClick = {
                    onGenerateClicked(eventName, eventStart,eventEnd,eventLocation,description)
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


@Preview
@Composable
private fun EventGenerateCardPreview() {
//    EventGenerateCard(
//        icon = ImageVector.vectorResource(R.drawable.calendar_icon),
//        menuName = "",
//        onGenerateClicked = { _, _, _, _, _ -> }
//    )
}