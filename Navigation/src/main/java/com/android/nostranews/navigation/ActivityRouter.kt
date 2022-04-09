package com.android.nostranews.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

private const val PACKAGE_NAME = "com.android.nostranews"
private const val FEATURES = "com.android.nostranews"

/**
 * Create an Intent with [Intent.ACTION_VIEW] to an [AddressableActivity].
 */
fun Context.intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(Intent.ACTION_VIEW)
        .setClassName(this, addressableActivity.className)
}
fun Context.startFeature(
    addressableActivity: AddressableActivity,
    @AnimRes enterResId: Int = android.R.anim.fade_in,
    @AnimRes exitResId: Int = android.R.anim.fade_out,
    options: Bundle? = null,
    body: Intent.() -> Unit) {

    val intent = intentTo(addressableActivity)
    intent.body()

    if (options == null) {
        val optionsCompat = ActivityOptionsCompat.makeCustomAnimation(this, enterResId, exitResId)
        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle())
    } else {
        ActivityCompat.startActivity(this, intent, options)
    }
}


interface AddressableActivity {
    val className: String
}
object Activities {
    object MainActivity : AddressableActivity {
        override val className = "com.android.nostranews.homenews.MainHomeActivity"
    }
    object ActivityNewsDetail : AddressableActivity {
        override val className = "com.android.nostranews.homenews.ActivityDetailNews"
    }
}