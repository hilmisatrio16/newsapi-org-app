package com.example.newsapiapp.model.article


import com.google.gson.annotations.SerializedName

data class ResponseDataSource(
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String
)