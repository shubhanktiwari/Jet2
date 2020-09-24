package com.example.article.data.network

import com.example.article.model.Result
import com.example.article.model.response.Article

class CallProvider(private val api:ArticleApi):SafeApiCallRequest() {

    suspend fun apiCallProvider(page:Int,limit:Int): Article{
        return when(val result = createCall { api.getArticle(page,limit) }){
            is Result.Success -> result.data
            is Result.Error -> throw result.error
        }
    }
}