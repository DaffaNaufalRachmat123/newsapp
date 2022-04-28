package com.android.saltnews.common.extension

import android.app.ActivityManager
import android.content.Context
import androidx.core.app.ActivityManagerCompat
import androidx.core.content.getSystemService

fun Context?.isLowRamDevice(): Boolean {
    val activityManager = this?.getSystemService<ActivityManager>()
    return if (activityManager != null)
        ActivityManagerCompat.isLowRamDevice(activityManager)
    else false
}