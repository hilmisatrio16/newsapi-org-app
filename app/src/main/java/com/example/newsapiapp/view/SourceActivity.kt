package com.example.newsapiapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiapp.databinding.ActivitySourceBinding
import com.example.newsapiapp.view.adapter.SourceAdapter
import com.example.newsapiapp.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySourceBinding
    private lateinit var sourceAdapter: SourceAdapter
    private lateinit var sourceViewModel: SourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sourceAdapter = SourceAdapter(ArrayList())

        sourceViewModel = ViewModelProvider(this)[SourceViewModel::class.java]

        setRecycleView()



    }

    private fun setRecycleView() {
        binding.rvSource.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val bundle = intent.extras
        val getCategory = bundle?.getString("category", "").toString()

        sourceViewModel.callApiSource(getCategory)
        binding.rvSource.adapter = sourceAdapter

        sourceViewModel.getDataSource().observe(this) {
            if (it != null) {
                sourceAdapter.setListSource(it)
            }
        }

        sourceAdapter.onClickSource = {
            val bundleSource = Bundle().apply {
                putString("source", it.name)
            }
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtras(bundleSource)
            startActivity(intent)
        }
    }
}