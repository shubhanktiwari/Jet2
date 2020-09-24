package com.example.article.model.response

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)