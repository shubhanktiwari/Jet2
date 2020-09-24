package com.example.article.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.article.data.repositories.ArticleRepository
import com.example.article.model.response.ArticleItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class MainViewModel(repository: ArticleRepository) : ViewModel() {

    val articleApi: Flow<PagingData<ArticleItem>> = repository.fetchArticleApi()
        .cachedIn(viewModelScope)
}