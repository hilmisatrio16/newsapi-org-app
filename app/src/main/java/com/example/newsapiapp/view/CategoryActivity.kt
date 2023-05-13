package com.example.newsapiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiapp.R
import com.example.newsapiapp.databinding.ActivityCategoryBinding
import com.example.newsapiapp.model.CategoryData
import com.example.newsapiapp.view.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() {

    lateinit var binding : ActivityCategoryBinding
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryAdapter = CategoryAdapter(ArrayList())

        setRecycleview()
    }

    private fun setRecycleview() {
        var listCategoryNews = arrayListOf(
            CategoryData("business", R.drawable.img_business),
            CategoryData("entertainment", R.drawable.img_entertainment),
            CategoryData("general", R.drawable.img_general),
            CategoryData("health", R.drawable.img_healty),
            CategoryData("science", R.drawable.img_science),
            CategoryData("sports", R.drawable.img_sports),
            CategoryData("technology", R.drawable.img_technology)
        )

        binding.rvCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.rvCategory.adapter = categoryAdapter

        categoryAdapter.setListNews(listCategoryNews)

        categoryAdapter.onClick = {
            Toast.makeText(this, "HAI", Toast.LENGTH_SHORT).show()

            var bundle = Bundle().apply {
                putString("category", it.name)
            }
            var intent = Intent(this, SourceActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}