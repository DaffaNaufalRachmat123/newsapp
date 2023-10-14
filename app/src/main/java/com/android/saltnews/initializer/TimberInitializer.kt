package com.android.saltnews.initializer

import android.content.Context
import androidx.startup.Initializer
import com.github.ajalt.timberkt.Timber
import com.android.saltnews.core.BuildConfig
import com.android.saltnews.log.DebugTree

class TimberInitializer : Initializer<Unit> {

    /**
     * Initializes and a component given the application [Context]
     *
     * @param context The application context.
     */
    override fun create(context: Context) {

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            //Timber.plant(FirebaseCrashlyticsTree())
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}