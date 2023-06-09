package com.example.newsapiapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapiapp.model.article.ResponseDataSource
import com.example.newsapiapp.model.article.Source
import com.example.newsapiapp.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private var api : ApiService) : ViewModel() {
    var liveDataSource : MutableLiveData<List<Source>?> = MutableLiveData()

    fun getDataSource()  : MutableLiveData<List<Source>?>{
        return liveDataSource
    }

    fun callApiSource(category : String){
        api.getAllSource(category)
            .enqueue(object : Callback<ResponseDataSource>{
                override fun onResponse(call: Call<ResponseDataSource>, response: Response<ResponseDataSource>) {
                    if(response.isSuccessful){
                        liveDataSource.postValue(response.body()!!.sources)
                        Log.d("HASIL_CATEGORY_NEWS", response.body()!!.sources.toString())
                    }else{
                        liveDataSource.postValue(null)
                    }
                }
                override fun onFailure(call: Call<ResponseDataSource>, t: Throwable) {
                    liveDataSource.postValue(null)
                }

            })
    }
}