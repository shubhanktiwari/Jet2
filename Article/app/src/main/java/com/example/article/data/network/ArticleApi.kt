package com.example.article.data.network

import com.example.article.model.response.Article
import com.example.article.utils.Constant.ARTICLE_BASE_URL
import com.example.article.utils.Constant.ARTICLE_GET_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET(ARTICLE_GET_URL)
    suspend fun getArticle(
        @Query("page") page: Int,
        @Query("limit") itemsPerPage: Int
    ): Response<Article>

    companion object {
        operator fun invoke(): ArticleApi {

            return Retrofit.Builder()
                .baseUrl(ARTICLE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleApi::class.java)
        }
    }
}