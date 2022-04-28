package com.android.saltnews.core.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @Expose
    @SerializedName("status")
    var status : String ,
    @Expose
    @SerializedName("totalResults")
    var totalResults : Int ,
    @Expose
    @SerializedName("articles")
    var articles : MutableList<Article> = arrayListOf()
)