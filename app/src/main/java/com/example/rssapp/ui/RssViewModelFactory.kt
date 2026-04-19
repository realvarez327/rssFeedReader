package com.example.rssapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.RssRepository

class RssViewModelFactory(private val repository: RssRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RssViewModel::class.java)){
            return RssViewModel(
                repo = repository
            ) as T
        }
        throw IllegalArgumentException("Not rss view model class! instead is $modelClass")
    }
}