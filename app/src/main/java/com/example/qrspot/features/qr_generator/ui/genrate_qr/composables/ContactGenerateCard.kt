package com.example.qrspot.features.qr_generator.ui.genrate_qr.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun ContactGenerateCard(
    icon: ImageVector,
    menuName: String,
    onGenerateClicked: (String, String, String, String, String, String, String, String, String, String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    modifier: Modifier = Modifier
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    var jobName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var website by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }

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
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "First Name",
                        fontSize = 14.sp,
                        color = Color(0xFFD9D9D9),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
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
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Last Name",
                        fontSize = 14.sp,
                        color = Color(0xFFD9D9D9),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
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
                }

            }
            Spacer(Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Company",
                        fontSize = 14.sp,
                        color = Color(0xFFD9D9D9),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = companyName,
                        onValueChange = { companyName = it },
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
                                text = "Enter company",
                                fontSize = 12.sp
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Job",
                        fontSize = 14.sp,
                        color = Color(0xFFD9D9D9),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = jobName,
                        onValueChange = { jobName = it },
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
                                text = "Enter job",
                                fontSize = 12.sp
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    )
                }

            }
            Spacer(Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Phone",
                        fontSize = 14.sp,
                        color = Color(0xFFD9D9D9),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
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
                                text = "Enter phone",
                                fontSize = 12.sp
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Email",
                        fontSize = 14.sp,
                        color = Color(0xFFD9D9D9),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
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
                                text = "Enter email",
                                fontSize = 12.sp
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    )
                }

            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Website",
                fontSize = 14.sp,
                color = Color(0xFFD9D9D9),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = website,
                onValueChange = { website = it },
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
                        text = "Enter website",
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
                text = "Address",
                fontSize = 14.sp,
                color = Color(0xFFD9D9D9),
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
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
                        text = "Enter address",
                        fontSize = 12.sp
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start)
            )
            Spacer(Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "City",
                        fontSize = 14.sp,
                        color = Color(0xFFD9D9D9),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
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
                                text = "Enter city",
                                fontSize = 12.sp
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Country",
                        fontSize = 14.sp,
                        color = Color(0xFFD9D9D9),
                        modifier = Modifier
                            .align(Alignment.Start)
                    )
                    Spacer(Modifier.height(5.dp))
                    OutlinedTextField(
                        value = country,
                        onValueChange = { country = it },
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
                                text = "Enter country",
                                fontSize = 12.sp
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Start)
                    )
                }

            }
            Spacer(Modifier.height(40.dp))
            ElevatedButton(
                onClick = {
                    onGenerateClicked(firstName, lastName, companyName, jobName, phone, email, website, address, city, country)
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
private fun ContactGenerateCardPreview() {
//    ContactGenerateCard(
//        icon = ImageVector.vectorResource(R.drawable.calendar_icon),
//        menuName = "",
//        onGenerateClicked = { _, _, _, _, _ , _, _, _, _, _ -> }
//    )
}