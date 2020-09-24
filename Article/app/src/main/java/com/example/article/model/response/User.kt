package com.example.article.model.response

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("designation")
    val designation: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("name")
    val name: String
)