package com.android.nostranews.common.extension

import android.content.res.Resources


val Float.dp: Float
    get() = (Resources.getSystem().displayMetrics.density * this)
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Float.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)
inline val Float.dpToPx: Float
    get() = this * Resources.getSystem().displayMetrics.density

inline val Int.dpToPx: Int
    get() = toFloat().dpToPx.toInt()