package com.example.qrspot.di

import com.example.qrspot.features.qr_scanner.data.repositories.HistoryRepositoryImpl
import com.example.qrspot.features.qr_scanner.domain.repositories.HistoryRepository
import com.example.qrspot.features.qr_scanner.ui.history.HistoryViewModel
import com.example.qrspot.features.qr_scanner.ui.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<HistoryRepository> { HistoryRepositoryImpl() }

    viewModel { HomeScreenViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}