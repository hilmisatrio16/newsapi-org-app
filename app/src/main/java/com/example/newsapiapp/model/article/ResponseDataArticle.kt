@file:Suppress("unused")

package com.example.newsapiapp.model.article


import com.google.gson.annotations.SerializedName

@Suppress("unused")
data class ResponseDataArticle(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)