package com.example.data

import com.example.rssapp.model.RssChannel
import com.example.rssapp.network.RssApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope



class RssRepository(
    private val service: RssApiService,
    private val channelDao: ChannelDao,
    private val feedParser: RssParser,
    private val converters: RoomConverters = RoomConverters()
) {
    //   private val channelsList = channelDao.getAllChannels().map { channelEntity -> converters.channelEntityToRssChannel(channelEntity) }

    suspend fun getAllFeeds() =
        coroutineScope {
            val channels  = channelDao.getAllChannels()
            channels.map { channelEntity ->
                async (Dispatchers.IO){
                    getFeed(channelEntity.url, channelEntity.id)
                }
            }.awaitAll().filterNotNull()
    }

    private suspend fun getFeed(url:String, channelId:Int): RssChannel?{
        val response = service.getFeedFromUrl(url)
        val stream = response.body()?.byteStream()?:return null
        return feedParser.parse(stream, channelId)
    }

}