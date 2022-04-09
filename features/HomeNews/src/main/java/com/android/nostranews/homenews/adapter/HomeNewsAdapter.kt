package com.android.nostranews.homenews.adapter

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.nostranews.common.extension.convertISODateToLocalDate
import com.android.nostranews.common.extension.convertMonthToWord
import com.android.nostranews.common.extension.loadImageRounded
import com.android.nostranews.core.model.home.Article
import com.android.nostranews.homenews.R
import com.android.nostranews.homenews.databinding.ItemNewsBinding
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
            if(item.urlToImage.isNullOrEmpty()){
                binding.imageNews.background = ContextCompat.getDrawable(context , R.drawable.bg_image_broken)
                binding.notFoundImage.visibility = View.VISIBLE
            } else {
                binding.imageNews.loadImageRounded(item.urlToImage , 10)
                binding.imageNews.background = null
                binding.notFoundImage.visibility = View.GONE
            }
            holder.itemView.setOnClickListener {
                onItemClicked.invoke(item)
            }
        }
    }
}