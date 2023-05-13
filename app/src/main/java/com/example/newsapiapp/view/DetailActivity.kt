package com.example.newsapiapp.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapiapp.R
import com.example.newsapiapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent.extras
        val getUrl = bundle?.getString("url", "").toString()
        setWebView(getUrl)

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView(url : String) {
        if(url.isNotEmpty()){
            binding.webViewDetailArticle.loadUrl(url)
            binding.webViewDetailArticle.settings.javaScriptEnabled = true
        }

    }
}