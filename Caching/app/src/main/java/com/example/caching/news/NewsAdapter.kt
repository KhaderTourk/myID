package com.example.caching.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caching.R

class NewsAdapter (val context: Context, private val list: ArrayList<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.discription.text = list[position].discription
        holder.author.text = list[position].author
    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val title: TextView = item.findViewById(R.id.tv_news_title)
        val discription: TextView = item.findViewById(R.id.tv_news_detail)
        val author: TextView = item.findViewById(R.id.tv_news_author)
    }
}