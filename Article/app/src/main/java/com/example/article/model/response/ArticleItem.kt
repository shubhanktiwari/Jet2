package com.example.article.model.response

import com.google.gson.annotations.SerializedName

data class ArticleItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("media")
    val media: List<Media>,
    @SerializedName("user")
    val user: List<User>
)