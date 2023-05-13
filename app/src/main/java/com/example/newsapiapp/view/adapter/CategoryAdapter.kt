package com.example.newsapiapp.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapiapp.databinding.ItemCategoryBinding
import com.example.newsapiapp.model.CategoryData

class CategoryAdapter(var listCategory : List<CategoryData>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    var onClick : ((CategoryData)->Unit)? = null

    class ViewHolder(var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvCategoryNews.text = listCategory[position].name
        Glide.with(holder.itemView).load(listCategory[position].image).into(holder.binding.imgCategory)
        holder.binding.itemCategory.setOnClickListener {
            onClick?.invoke(listCategory[position])
        }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    fun setListNews(list: List<CategoryData>){
        listCategory = list
    }
}