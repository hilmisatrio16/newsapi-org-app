package com.example.newsapiapp.model.article


import com.google.gson.annotations.SerializedName

data class SourceArticle(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)