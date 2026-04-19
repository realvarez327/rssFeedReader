package com.example.rssapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.RssRepository
import com.example.rssapp.model.FeedItem
import com.example.rssapp.model.RssChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RssViewModel (private val repo: RssRepository): ViewModel(){

    init {
        reloadChannels()
    }

    private val _channels = MutableStateFlow<List<RssChannel>>(emptyList())
    val channels: StateFlow<List<RssChannel>> =  _channels

    private fun reloadChannels(){
        viewModelScope.launch {
            _channels.value = repo.getAllFeeds()
        }
    }
}