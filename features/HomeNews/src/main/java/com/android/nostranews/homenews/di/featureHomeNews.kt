package com.android.nostranews.homenews.di

import com.android.nostranews.core.di.provideApiService
import com.android.nostranews.homenews.HomeViewModel
import com.android.nostranews.homenews.api.HomeApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeNews = module {
    single { provideApiService<HomeApi>(get()) }
    viewModel { HomeViewModel(get() , get()) }
}