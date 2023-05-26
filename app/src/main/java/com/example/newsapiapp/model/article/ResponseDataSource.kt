@file:Suppress("unused", "unused")

package com.example.newsapiapp.model.article


import com.google.gson.annotations.SerializedName

@Suppress("unused")
data class ResponseDataSource(
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String
)