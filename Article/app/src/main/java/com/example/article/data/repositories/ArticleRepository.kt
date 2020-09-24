package com.example.article.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.article.data.network.CallProvider
import com.example.article.model.response.ArticleItem
import com.example.article.utils.Constant.NETWORK_PAGE_SIZE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class ArticleRepository(private val apiCallProvider: CallProvider) {

    fun fetchArticleApi(): Flow<PagingData<ArticleItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { ArticlePagingSource(apiCallProvider) }
        ).flow
    }
}