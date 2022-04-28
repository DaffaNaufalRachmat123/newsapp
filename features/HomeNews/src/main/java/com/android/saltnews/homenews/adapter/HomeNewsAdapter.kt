package com.android.saltnews.homenews.adapter

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.android.saltnews.common.extension.convertISODateToLocalDate
import com.android.saltnews.common.extension.convertMonthToWord
import com.android.saltnews.common.extension.loadImageRounded
import com.android.saltnews.core.model.home.Article
import com.android.saltnews.homenews.R
import com.android.saltnews.homenews.databinding.ItemNewsBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class HomeNewsAdapter ( val context : Context ,  val onItemClicked : (Article) -> Unit) : BaseQuickAdapter<Article , BaseViewHolder>(R.layout.item_news) {
    override fun convert(holder: BaseViewHolder, item: Article) {
        val itemBinding = ItemNewsBinding.bind(holder.itemView);
        itemBinding?.let { binding ->
            val published_time = "${item.publishedAt.convertISODateToLocalDate().split("-")[2]} ${item.publishedAt.convertISODateToLocalDate().split("-")[1].convertMonthToWord()} ${item.publishedAt.convertISODateToLocalDate().split("-")[0]}"
            binding.authorNewsText.text = if(item.author.isNullOrEmpty()) "No Author" else item.author
            binding.timestampNewsText.text = published_time
            binding.titleNewsText.text = item.title
            binding.imageNews.loadImageRounded(item.urlToImage , 10)
            binding.imageNews.background = null
            holder.itemView.setOnClickListener {
                onItemClicked.invoke(item)
            }
        }
    }
}