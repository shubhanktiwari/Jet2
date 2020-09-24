package com.example.article.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.article.R
import com.example.article.databinding.LoadStateFooterViewItemBinding

class ArticleLoadStateViewHolder(
        private val binding: LoadStateFooterViewItemBinding,
        private val context:Context,
        retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = context.resources.getString(R.string.getErrorMessage)
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ArticleLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.load_state_footer_view_item, parent, false)
            val binding = LoadStateFooterViewItemBinding.bind(view)
            return ArticleLoadStateViewHolder(binding,parent.context, retry)
        }
    }
}