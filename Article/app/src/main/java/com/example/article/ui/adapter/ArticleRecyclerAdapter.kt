package com.example.article.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.article.R
import com.example.article.model.response.ArticleItem

class ArticleRecyclerAdapter: PagingDataAdapter<ArticleItem, ArticleViewHolder>(ARTICLE_COMPARATOR) {

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { holder.initialize(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.article_recycler, parent, false)
        return ArticleViewHolder(view,parent.context)
    }

    companion object {
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<ArticleItem>() {
            override fun areItemsTheSame(
                oldItem: ArticleItem,
                newItem: ArticleItem
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ArticleItem,
                newItem: ArticleItem
            ): Boolean =
                oldItem == newItem
        }
    }
}