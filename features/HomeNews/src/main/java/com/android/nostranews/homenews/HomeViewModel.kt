package com.android.nostranews.homenews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.nostranews.common.base.BaseViewModel
import com.android.nostranews.common.extension.errorMessage
import com.android.nostranews.common.utils.ViewState
import com.android.nostranews.common.utils.setError
import com.android.nostranews.common.utils.setLoading
import com.android.nostranews.common.utils.setSuccess
import com.android.nostranews.core.AppDispatchers
import com.android.nostranews.core.model.home.ArticleResponse
import com.android.nostranews.homenews.api.HomeApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel ( val api : HomeApi , val dispatchers: AppDispatchers) : BaseViewModel() {
    val articleResponse = MutableLiveData<ViewState<ArticleResponse>>()
    val articleMoreResponse = MutableLiveData<ViewState<ArticleResponse>>()

    fun getTopHeadlines(country : String , category : String , page : String , pageSize : String , isTest : Boolean = false){
        if(isTest){
            runBlocking {
                runCatching {
                    articleResponse.setLoading()
                    api.getTopHeadlines(country , category , page , pageSize , BuildConfig.BASE_API_KEY)
                }.onSuccess {
                    articleResponse.setSuccess(it)
                }.onFailure {
                    articleResponse.setError(it.errorMessage())
                }
            }
        } else {
            viewModelScope.launch(dispatchers.main){
                runCatching {
                    articleResponse.setLoading()
                    api.getTopHeadlines(country , category , page , pageSize , BuildConfig.BASE_API_KEY)
                }.onSuccess {
                    articleResponse.setSuccess(it)
                }.onFailure {
                    articleResponse.setError(it.errorMessage())
                }
            }
        }
    }

    fun getTopHeadlinesMore(next_page : String , isTest : Boolean = false){
        if(isTest){
            runBlocking {
                runCatching {
                    api.getTopHeadlinesMore(next_page)
                }.onSuccess {
                    articleMoreResponse.setSuccess(it)
                }.onFailure {
                    articleResponse.setError(it.errorMessage())
                }
            }
        } else {
            viewModelScope.launch(dispatchers.main){
                runCatching {
                    api.getTopHeadlinesMore(next_page)
                }.onSuccess {
                    articleMoreResponse.setSuccess(it)
                }.onFailure {
                    articleMoreResponse.setError(it.errorMessage())
                }
            }
        }
    }
}