package com.example.newsapiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiapp.R
import com.example.newsapiapp.databinding.ActivitySourceBinding
import com.example.newsapiapp.model.article.Source
import com.example.newsapiapp.view.adapter.SourceAdapter
import com.example.newsapiapp.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {
    lateinit var binding: ActivitySourceBinding
    lateinit var sourceAdapter: SourceAdapter
    lateinit var sourceViewModel: SourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sourceAdapter = SourceAdapter(ArrayList())

        sourceViewModel = ViewModelProvider(this).get(SourceViewModel::class.java)

        setRecycleView()



    }

    private fun setRecycleView() {
        binding.rvSource.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val bundle = intent.extras
        val getCategory = bundle?.getString("category", "").toString()

        sourceViewModel.callApiSource(getCategory)
        binding.rvSource.adapter = sourceAdapter

        sourceViewModel.getDataSource().observe(this, Observer {
           if(it != null){
               sourceAdapter.setListSource(it)
           }
        })

        sourceAdapter.onClickSource = {
            var bundle = Bundle().apply {
                putString("source", it.name)
            }
            var intent = Intent(this, ArticleActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}