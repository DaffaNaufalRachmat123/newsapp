package com.android.saltnews.homenews

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.android.saltnews.common.base.BaseActivity
import com.android.saltnews.common.extension.binding
import com.android.saltnews.common.extension.convertISODateToLocalDate
import com.android.saltnews.common.extension.convertMonthToWord
import com.android.saltnews.common.extension.loadImageRounded
import com.android.saltnews.core.model.home.Article
import com.android.saltnews.homenews.databinding.ActivityNewsDetailBinding

class ActivityDetailNews : BaseActivity<HomeViewModel>(R.layout.activity_news_detail) {
    private val binding by binding<ActivityNewsDetailBinding>()
    private var articleDetail : Article? = null
    override fun getViewModel() = HomeViewModel::class
    override fun observerViewModel() {

    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        articleDetail = NewsRepository.newInstance().getArticleDetail()
        articleDetail?.let { item ->
            val published_time = "${item.publishedAt.convertISODateToLocalDate().split("-")[2]} ${item.publishedAt.convertISODateToLocalDate().split("-")[1].convertMonthToWord()} ${item.publishedAt.convertISODateToLocalDate().split("-")[0]}"
            binding.authorNewsText.text = if(item.author.isNullOrEmpty()) "No Author" else item.author
            binding.timestampNewsText.text = published_time
            binding.titleNewsText.text = item.title
            binding.descriptionNewsText.text = item.description
            if(item.urlToImage.isNullOrEmpty()){
                binding.imageNews.background = ContextCompat.getDrawable(applicationContext , R.drawable.bg_image_broken)
                binding.notFoundImage.visibility = View.VISIBLE
            } else {
                binding.imageNews.loadImageRounded(item.urlToImage , 10)
                binding.imageNews.background = null
                binding.notFoundImage.visibility = View.GONE
            }
        }
        binding.icBack.setOnClickListener {
            onBackPressed()
        }
    }
}