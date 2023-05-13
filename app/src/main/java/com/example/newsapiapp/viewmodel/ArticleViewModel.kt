package com.example.newsapiapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapiapp.model.article.Article
import com.example.newsapiapp.model.article.ResponseDataArticle
import com.example.newsapiapp.network.ApiService
import com.example.newsapiapp.network.NetworkClient
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(var api : ApiService) : ViewModel() {

    lateinit var liveDataArticle : MutableLiveData<List<Article>>

    init {
        liveDataArticle = MutableLiveData()
    }

    fun getDataArticle() : MutableLiveData<List<Article>>{
        return liveDataArticle
    }

    fun callApiArticle(source : String){
        api.getAllArticle(source).enqueue(
            object : Callback<ResponseDataArticle>{
                override fun onResponse(
                    call: Call<ResponseDataArticle>,
                    response: Response<ResponseDataArticle>
                ) {
                    if(response.isSuccessful){
                        liveDataArticle.postValue(response.body()!!.articles)
                    }else{
                        liveDataArticle.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataArticle>, t: Throwable) {
                    liveDataArticle.postValue(null)
                }

            }
        )
    }
}