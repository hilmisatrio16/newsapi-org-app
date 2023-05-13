package com.example.newsapiapp.network

import com.example.newsapiapp.model.article.ResponseDataArticle
import com.example.newsapiapp.model.article.ResponseDataSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines/sources")
    fun getAllSource(
        @Query("category") category : String,
        @Query("apiKey") apiKey : String = "309d87507d5143eea76d09c1aa18ad5a"
    ) : Call<ResponseDataSource>

    @GET("top-headlines")
    fun getAllArticle(
        @Query("sources") sources : String,
        @Query("apiKey") apiKey : String = "309d87507d5143eea76d09c1aa18ad5a"
    ) : Call<ResponseDataArticle>

}