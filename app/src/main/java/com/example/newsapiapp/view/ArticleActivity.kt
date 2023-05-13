package com.example.newsapiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiapp.R
import com.example.newsapiapp.databinding.ActivityArticleBinding
import com.example.newsapiapp.view.adapter.ArticleAdapter
import com.example.newsapiapp.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {

    lateinit var binding : ActivityArticleBinding
    lateinit var articleViewModel: ArticleViewModel
    lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        articleAdapter = ArticleAdapter(ArrayList())

        setRecycleView()
    }

    private fun setRecycleView() {
        binding.rvArticle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvArticle.adapter = articleAdapter

        val bundle = intent.extras
        val getSource = bundle?.getString("source", "").toString()

        articleViewModel.callApiArticle(getSource)

        articleViewModel.getDataArticle().observe(this, Observer {
            if(it != null){
                articleAdapter.setDataArticle(it)
            }
        })

        articleAdapter.onClickArticle = {
            val bundle = Bundle().apply {
                putString("url", it.url)
            }
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}