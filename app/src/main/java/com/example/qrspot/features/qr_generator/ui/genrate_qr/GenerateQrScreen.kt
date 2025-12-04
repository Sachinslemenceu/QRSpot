package com.example.qrspot.features.qr_generator.ui.genrate_qr

import android.content.Intent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrspot.R
import com.example.qrspot.features.qr_generator.ui.genrate_qr.composables.BusinessGenerateCard
import com.example.qrspot.features.qr_generator.ui.genrate_qr.composables.CommonGenerateCard
import com.example.qrspot.features.qr_generator.ui.genrate_qr.composables.ContactGenerateCard
import com.example.qrspot.features.qr_generator.ui.genrate_qr.composables.EventGenerateCard
import com.example.qrspot.features.qr_generator.ui.genrate_qr.composables.ShowQrSection
import com.example.qrspot.features.qr_generator.ui.genrate_qr.composables.WifiGenerateCard
import com.example.qrspot.features.qr_generator.ui.genrate_qr.models.GenerateQrDetailItem
import com.example.qrspot.ui.theme.darkGrey300
import com.example.qrspot.ui.theme.darkGrey500
import com.example.qrspot.ui.theme.yellow500
import dev.shreyaspatil.capturable.controller.rememberCaptureController

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GenerateQrScreen(
    onBackClick: () -> Unit,
    menuName: String,
    uiState: GenerateQrScreenUiState,
    onEvent: (GenerateQrScreenUiEvent) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    val menu = when (menuName) {
        "Scan" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.text_icon),
            fieldTitle = "Text",
            fieldLabel = "Enter Text"
        )

        "Website" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.www_icon),
            fieldTitle = "Website URL",
            fieldLabel = "www.qrcode.com"
        )

        "Wi-Fi" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.wifi_icon),
            isWifiMenu = true
        )

        "Event" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.calendar_icon),
            isEventMenu = true
        )

        "Contact" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.contact_icon),
            isContactMenu = true
        )

        "Business" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.office_icon),
            isBusinessMenu = true
        )

        "Location" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.map_icon),
            fieldTitle = "Location URL",
        )

        "WhatsApp" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.whatsapp_icon),
            fieldTitle = "WhatsApp Number",
            fieldLabel = "Enter number"
        )

        "Email" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.mail_icon),
            fieldTitle = "Email",
            fieldLabel = "Enter email address"
        )

        "twitter" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.twiter_icon),
            fieldTitle = "Username",
            fieldLabel = "Enter twitter username"
        )

        "Instagram" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.insta_icon),
            fieldTitle = "Username",
            fieldLabel = "Enter instagram username"
        )

        "Telephone" -> GenerateQrDetailItem(
            icon = ImageVector.vectorResource(R.drawable.phone_icon),
            fieldTitle = "Phone Number",
            fieldLabel = "+92xxxxxxxxxx"
        )

        else -> {
            GenerateQrDetailItem(
                icon = ImageVector.vectorResource(R.drawable.text_icon),
                fieldTitle = "Unknown"
            )
        }
    }
    Scaffold(
        containerColor = darkGrey300,
    ) { innerPadding ->
        if (uiState.qrCode == null) {
            with(sharedTransitionScope) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                                .background(
                                    color = darkGrey500,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable(onClick = onBackClick)
                                .padding(3.dp)
                                .size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                                contentDescription = "Back",
                                tint = yellow500
                            )
                        }
                        Text(
                            text = menuName,
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .sharedElement(
                                    sharedContentState = rememberSharedContentState(key = menuName),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(500)
                                    }
                                )
                        )
                    }
                    if (menu.isWifiMenu) {
                        Spacer(Modifier.weight(0.3f))
                        WifiGenerateCard(
                            icon = menu.icon,
                            menuName = menuName,
                            onGenerateClicked = { network, password ->
                                onEvent(GenerateQrScreenUiEvent.GenerateQrCodeFromWifiForm(network,password))
                            },
                            animatedVisibilityScope = animatedVisibilityScope,
                            sharedTransitionScope = sharedTransitionScope
                        )
                        Spacer(Modifier.weight(0.7f))
                    } else if (menu.isEventMenu) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .verticalScroll(rememberScrollState())

                        ) {
                            Spacer(Modifier.height(40.dp))
                            EventGenerateCard(
                                icon = menu.icon,
                                menuName = menuName,
                                onGenerateClicked = { eventName, eventStart,eventEnd,eventLocation,description->
                                    onEvent(GenerateQrScreenUiEvent.GenerateQrCodeFromEventFormContactForm(
                                        eventName = eventName,
                                        startDateTime = eventStart,
                                        endDateTime = eventEnd,
                                        eventLocation = eventLocation,
                                        description
                                    ))
                                },
                                animatedVisibilityScope = animatedVisibilityScope,
                                sharedTransitionScope = sharedTransitionScope
                            )
                            Spacer(Modifier.height(40.dp))

                        }
                    } else if (menu.isContactMenu) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .verticalScroll(rememberScrollState())

                        ) {
                            Spacer(Modifier.height(40.dp))
                            ContactGenerateCard(
                                icon = menu.icon,
                                menuName = menuName,
                                onGenerateClicked = { firstName, lastName, companyName, jobName, phone, email, website, address, city, country->
                                    onEvent(GenerateQrScreenUiEvent.GenerateQrCodeFromContactForm(
                                        firstName = firstName,
                                        lastName = lastName,
                                        company = companyName,
                                        job = jobName,
                                        phoneNumber = phone,
                                        email = email,
                                        website = website,
                                        address = address,
                                        city = city,
                                        country = country
                                    ))
                                },
                                animatedVisibilityScope = animatedVisibilityScope,
                                sharedTransitionScope = sharedTransitionScope
                            )
                            Spacer(Modifier.height(40.dp))

                        }
                    } else if (menu.isBusinessMenu) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .verticalScroll(rememberScrollState())

                        ) {
                            Spacer(Modifier.height(40.dp))
                            BusinessGenerateCard(
                                icon = menu.icon,
                                menuName = menuName,
                                onGenerateClicked = { companyName, industryName, phone, email, website, address, city, country->
                                    onEvent(GenerateQrScreenUiEvent.GenerateQrCodeFromBusinessForm(
                                        companyName = companyName,
                                        industry = industryName,
                                        phoneNumber = phone,
                                        email = email,
                                        website = website,
                                        address = address,
                                        city = city,
                                        country = country
                                    ))
                                },
                                animatedVisibilityScope = animatedVisibilityScope,
                                sharedTransitionScope = sharedTransitionScope
                            )
                            Spacer(Modifier.height(40.dp))

                        }
                    } else {
                        Spacer(Modifier.weight(0.3f))
                        CommonGenerateCard(
                            icon = menu.icon,
                            fieldTitle = menu.fieldTitle ?: "Text",
                            fieldLabel = menu.fieldLabel ?: "Enter Text",
                            menuName = menuName,
                            onGenerateClicked = {
                                onEvent(GenerateQrScreenUiEvent.GenerateQrCodeFromText(it))
                            },
                            animatedVisibilityScope = animatedVisibilityScope,
                            sharedTransitionScope = sharedTransitionScope
                        )
                        Spacer(Modifier.weight(0.7f))
                    }

                }

            }
        } else {
            ShowQrSection(
                qrCode = uiState.qrCode,
                onBackClick = {
                    onEvent(GenerateQrScreenUiEvent.OnBackClick)
                },
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}


@Preview
@Composable
private fun GenerateQrScreenPreview() {
//    GenerateQrScreen(
//        onBackClick = {}
//    )
}



