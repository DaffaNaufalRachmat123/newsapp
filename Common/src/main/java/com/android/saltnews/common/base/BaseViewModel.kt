package com.android.saltnews.common.base

import androidx.lifecycle.*
import com.android.saltnews.common.extension.errorMessage
import com.android.saltnews.common.extension.runOnUi
import com.android.saltnews.core.AppDispatchers
import com.github.ajalt.timberkt.i
import com.github.ajalt.timberkt.w
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    override fun onCleared() {
        super.onCleared()
        i { "ViewModel Cleared" }
    }
}