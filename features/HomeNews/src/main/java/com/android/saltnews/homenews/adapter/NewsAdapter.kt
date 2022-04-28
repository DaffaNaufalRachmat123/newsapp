package com.android.saltnews.homenews.adapter

import com.android.saltnews.core.model.home.Article
import com.android.saltnews.homenews.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class NewsAdapter : BaseQuickAdapter<Article , BaseViewHolder>(R.layout.item_news) {
    override fun convert(holder: BaseViewHolder, item: Article) {

    }
}