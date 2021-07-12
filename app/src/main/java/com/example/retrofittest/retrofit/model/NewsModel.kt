package com.example.retrofittest.retrofit.model

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("articles")
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)