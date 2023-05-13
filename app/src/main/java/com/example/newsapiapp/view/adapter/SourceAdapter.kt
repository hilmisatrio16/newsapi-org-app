package com.example.newsapiapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiapp.databinding.ItemSourceBinding
import com.example.newsapiapp.model.article.Source

class SourceAdapter(private var listSource : List<Source>) : RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    var onClickSource : ((Source)-> Unit)? = null

    class ViewHolder(var binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceAdapter.ViewHolder {
        val view = ItemSourceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SourceAdapter.ViewHolder, position: Int) {
        holder.binding.tvSourceNews.text = listSource[position].name

        holder.binding.itemSource.setOnClickListener {
            onClickSource?.invoke(listSource[position])
        }
    }

    override fun getItemCount(): Int {
        return listSource.size
    }

    fun setListSource(list: List<Source>){
        this.listSource = list
        notifyDataSetChanged()

        Log.d("HASIL_SOURCE", list.toString())
    }
}