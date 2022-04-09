package com.android.nostranews.homenews.api

import com.android.nostranews.core.model.home.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface HomeApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country : String ,
        @Query("category") category : String,
        @Query("page") page : String ,
        @Query("pageSize") pageSize : String,
        @Query("apiKey") apiKey : String
    ) : ArticleResponse

    @GET
    suspend fun getTopHeadlinesMore(
        @Url next_page : String
    ) : ArticleResponse
}