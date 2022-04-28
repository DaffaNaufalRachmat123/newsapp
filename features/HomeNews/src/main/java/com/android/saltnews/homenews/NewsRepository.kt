package com.android.saltnews.homenews

import com.android.saltnews.core.model.home.Article

class NewsRepository {
    private var articleDetail : Article? = null
    companion object {
        var instance : NewsRepository? = null
        @JvmStatic
        fun newInstance() : NewsRepository {
            if(instance == null)
                instance = NewsRepository()
            return instance!!
        }
    }
    fun setArticleDetail(article: Article){
        this.articleDetail = article
    }
    fun getArticleDetail() = articleDetail
}