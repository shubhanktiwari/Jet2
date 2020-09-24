package com.example.article.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.article.R
import com.example.article.data.network.ArticleApi
import com.example.article.data.network.CallProvider
import com.example.article.data.repositories.ArticleRepository
import com.example.article.databinding.ActivityMainBinding
import com.example.article.ui.adapter.ArticleLoadStateAdapter
import com.example.article.ui.adapter.ArticleRecyclerAdapter
import com.example.article.ui.viewmodel.MainViewModel
import com.example.article.ui.viewmodel.MainViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var articleJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val api = ArticleApi()
        val callProvider = CallProvider(api)
        val repository = ArticleRepository(callProvider)
        val factory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        buildArticlesRecycler()
    }

    private fun buildArticlesRecycler() {

        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        val articleAdapter = ArticleRecyclerAdapter()
        binding.articleRecycler.adapter = articleAdapter.withLoadStateHeaderAndFooter(
            header = ArticleLoadStateAdapter { articleAdapter.retry() },
            footer = ArticleLoadStateAdapter { articleAdapter.retry() }
        )

        binding.articleRecycler.layoutManager = manager
        binding.articleRecycler.setHasFixedSize(true)

        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            viewModel.articleApi.collect {
                articleAdapter.submitData(it)
            }
        }
    }
}