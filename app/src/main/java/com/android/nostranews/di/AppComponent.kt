package com.android.nostranews.di

import com.android.nostranews.core.di.coreModule
import com.android.nostranews.homenews.di.featureHomeNews


val appComponent = listOf(
    coreModule,
    featureHomeNews
)