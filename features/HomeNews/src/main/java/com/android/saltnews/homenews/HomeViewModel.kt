package com.android.saltnews.homenews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.saltnews.common.base.BaseViewModel
import com.android.saltnews.common.extension.errorMessage
import com.android.saltnews.common.utils.ViewState
import com.android.saltnews.common.utils.setError
import com.android.saltnews.common.utils.setLoading
import com.android.saltnews.common.utils.setSuccess
import com.android.saltnews.core.AppDispatchers
import com.android.saltnews.core.model.home.ArticleResponse
import com.android.saltnews.homenews.api.HomeApi
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