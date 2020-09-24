package com.example.article.data.repositories

import androidx.paging.PagingSource
import com.example.article.data.network.CallProvider
import com.example.article.model.response.ArticleItem
import com.example.article.utils.Constant.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class ArticlePagingSource(
    private val apiCallProvider: CallProvider
) : PagingSource<Int, ArticleItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleItem> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = apiCallProvider.apiCallProvider(position,params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}