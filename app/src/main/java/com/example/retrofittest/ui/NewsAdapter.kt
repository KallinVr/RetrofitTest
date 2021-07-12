package com.example.retrofittest.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofittest.databinding.ItemBinding
import com.example.retrofittest.retrofit.model.Article


class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var newsList = mutableListOf<Article>()

    @JvmName("setNewsList1")
    fun setNewsList(news: List<Article>){
        this.newsList = news.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater,parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.binding.itemTitleText.text = news.title
        holder.binding.itemTitleText.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
            startActivity(holder.itemView.context, myIntent, null)
        }
        Glide.with(holder.itemView.context)
            .load(news.urlToImage)
            .into(holder.binding.itemImage)
        holder.binding.itemDescriptionText.text = news.description

    }

    override fun getItemCount(): Int = newsList.size


    class NewsViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root){

    }


}