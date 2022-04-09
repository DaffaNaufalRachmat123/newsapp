package com.android.nostranews.core.model.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @Expose
    @SerializedName("source")
    var source : Source ,
    @Expose
    @SerializedName("author")
    var author : String ,
    @Expose
    @SerializedName("title")
    var title : String ,
    @Expose
    @SerializedName("description")
    var description : String ,
    @Expose
    @SerializedName("url")
    var url : String ,
    @Expose
    @SerializedName("urlToImage")
    var urlToImage : String ,
    @Expose
    @SerializedName("publishedAt")
    var publishedAt : String ,
    @Expose
    @SerializedName("content")
    var content : String
)

data class Source(
    @Expose
    @SerializedName("id")
    var id : String,
    @Expose
    @SerializedName("name")
    var name : String
)