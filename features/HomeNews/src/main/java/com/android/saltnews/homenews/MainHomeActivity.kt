package com.android.saltnews.homenews

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.common.view.statelayout.StateLayout
import com.android.saltnews.common.base.BaseActivity
import com.android.saltnews.common.extension.binding
import com.android.saltnews.common.utils.ViewState
import com.android.saltnews.common.widget.CustomLoadMoreView
import com.android.saltnews.homenews.adapter.HomeNewsAdapter
import com.android.saltnews.homenews.databinding.MainHomeActivityBinding
import com.android.saltnews.navigation.Activities
import com.android.saltnews.navigation.startFeature

class MainHomeActivity : BaseActivity<HomeViewModel>(R.layout.main_home_activity) {
    private val binding by binding<MainHomeActivityBinding>()
    private val country = "us"
    private val category = "technology"
    private var page = 1
    private var totalResult = 0
    private val pageSize = 5
    private var newsAdapter : HomeNewsAdapter? = null
    private val stateLayout by lazy {
        StateLayout(this)
            .wrap(binding.recyclerViewNews)
            .showLoading()
    }
    override fun getViewModel() = HomeViewModel::class
    private fun buildNextPageUrl(page : String, pageSize : String) : String {
        return "${BuildConfig.BASE_URL}top-headlines?country=${country}&category=${category}&page=${page}&pageSize=${pageSize}&apiKey=${BuildConfig.BASE_API_KEY}"
    }
    override fun observerViewModel() {
        viewModel.articleResponse.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    stateLayout.showLoading(showText = true , loadingText = "Memuat berita...")
                }
                is ViewState.Success -> {
                    if(state.data.articles.size > 0){
                        stateLayout.showContent()
                        newsAdapter?.setNewData(state.data.articles)
                        var nextPage : String? = null
                        if(totalResult < state.data.totalResults){
                            totalResult += pageSize
                            page += 1
                            nextPage = buildNextPageUrl(page.toString() , pageSize.toString())
                        }
                        canLoadMore(nextPage)
                    } else {
                        stateLayout.showEmpty(noDataText = "Tidak ada berita terbaru")
                    }
                }
                is ViewState.Failed -> {
                    stateLayout.showError()
                    stateLayout.onRetry() {
                        viewModel.getTopHeadlines(country,category,page.toString(),pageSize.toString())
                    }
                }
            }
        }
        viewModel.articleMoreResponse.onResult { state ->
            when (state) {
                is ViewState.Loading -> {}
                is ViewState.Success -> {
                    if(state.data.articles.size > 0){
                        newsAdapter?.addData(state.data.articles)
                    }
                    var nextPage : String? = null
                    if(totalResult < state.data.totalResults){
                        totalResult += pageSize
                        page += 1
                        nextPage = buildNextPageUrl(page.toString() , pageSize.toString())
                    }
                    canLoadMore(nextPage)
                }
                is ViewState.Failed -> {}
            }
        }
    }

    private fun canLoadMore(next_page : String?){
        if(next_page == null){
            newsAdapter?.setEnableLoadMore(false)
        } else {
            newsAdapter?.setEnableLoadMore(true)
            newsAdapter?.setLoadMoreView(CustomLoadMoreView())
            newsAdapter?.setOnLoadMoreListener {
                if (next_page == null) {
                    newsAdapter?.loadMoreEnd()
                } else {
                    viewModel.getTopHeadlinesMore(next_page)
                }
            }
        }
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        newsAdapter = HomeNewsAdapter (this) {
            NewsRepository.newInstance().setArticleDetail(it)
            startFeature(Activities.ActivityNewsDetail) {}
        }
        binding.swipeRefresh.setOnRefreshListener {
            page = 1
            viewModel.getTopHeadlines(country , category , page.toString() , pageSize.toString())
            binding.swipeRefresh.isRefreshing = false
        }
        binding.recyclerViewNews.apply {
            setHasFixedSize(true)
            itemAnimator = null
            layoutManager = LinearLayoutManager(applicationContext , RecyclerView.VERTICAL , false)
            adapter = newsAdapter
        }
        viewModel.getTopHeadlines(country,category,page.toString(),pageSize.toString())
    }
}