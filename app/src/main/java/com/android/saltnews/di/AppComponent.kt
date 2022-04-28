package com.android.saltnews.di

import com.android.saltnews.core.di.coreModule
import com.android.saltnews.homenews.di.featureHomeNews


val appComponent = listOf(
    coreModule,
    featureHomeNews
)