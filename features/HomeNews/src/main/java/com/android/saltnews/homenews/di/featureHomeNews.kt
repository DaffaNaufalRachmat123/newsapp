package com.android.saltnews.homenews.di

import com.android.saltnews.core.di.provideApiService
import com.android.saltnews.homenews.HomeViewModel
import com.android.saltnews.homenews.api.HomeApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeNews = module {
    single { provideApiService<HomeApi>(get()) }
    viewModel { HomeViewModel(get() , get()) }
}