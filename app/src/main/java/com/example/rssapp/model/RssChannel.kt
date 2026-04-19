package com.example.rssapp.model

data class RssChannel (
    val title:String,
    val link: String,
    val description:String,
    val channelChildren: MutableList<FeedItem> = mutableListOf(),
    val id:Int
)