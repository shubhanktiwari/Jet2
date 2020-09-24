package com.example.article.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.article.data.repositories.ArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Suppress("UNCHECKED_CAST")
@ExperimentalCoroutinesApi
class MainViewModelFactory constructor(private val repository: ArticleRepository)
    : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}