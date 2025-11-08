package com.example.qrspot.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qrspot.features.qr.ui.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeScreenViewModel() }
}