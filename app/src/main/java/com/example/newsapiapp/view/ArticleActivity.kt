package com.example.newsapiapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiapp.databinding.ActivityArticleBinding
import com.example.newsapiapp.view.adapter.ArticleAdapter
import com.example.newsapiapp.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityArticleBinding
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articleViewModel = ViewModelProvider(this)[ArticleViewModel::class.java]

        articleAdapter = ArticleAdapter(ArrayList())

        setRecycleView()
    }

    private fun setRecycleView() {
        binding.rvArticle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvArticle.adapter = articleAdapter

        val bundle = intent.extras
        val getSource = bundle?.getString("source", "").toString()

        articleViewModel.callApiArticle(getSource)

        articleViewModel.getDataArticle().observe(this) {
            if (it != null) {
                articleAdapter.setDataArticle(it)
            }
        }

        articleAdapter.onClickArticle = {
            val bundleUrl = Bundle().apply {
                putString("url", it.url)
            }
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundleUrl)
            startActivity(intent)
        }
    }
}