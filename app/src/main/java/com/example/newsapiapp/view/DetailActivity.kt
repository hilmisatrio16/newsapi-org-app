@file:Suppress("unused", "unused", "unused")

package com.example.newsapiapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapiapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("unused", "unused", "unused", "unused", "unused")
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

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