package com.android.saltnews.initializer

import android.content.Context
import androidx.startup.Initializer
import com.github.ajalt.timberkt.Timber
import com.android.saltnews.core.BuildConfig
import com.android.saltnews.log.DebugTree

class TimberInitializer : Initializer<Unit> {

//    @Throws(IOException::class)
//    private fun createFileLoggingTree(context: Context): Timber.Tree {
//        val loggingFile = StorageHelper.getLogsCache(context)
//        return FileLoggerTree.Builder()
//            .withFileName("${context.packageName}.log")
//            .withDirName(loggingFile.absolutePath)
//            .withSizeLimit(logSizeLimit)
//            .withFileLimit(1)
//            .withMinPriority(logLevel)
//            .appendToFile(true)
//            .build()
//    }

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