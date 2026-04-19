package com.example.data

import com.example.rssapp.model.RssChannel

class RoomConverters {
    fun channelEntityToRssChannel(channelEntity: ChannelEntity): RssChannel{
        return RssChannel(
            title = channelEntity.channelName,
            link = channelEntity.url,
            description = channelEntity.description,
            id = channelEntity.id
        )
    }
    fun rssChannelToChannelEntity(rssChannel: RssChannel): ChannelEntity{
        return ChannelEntity(
            url = rssChannel.link,
            channelName = rssChannel.title,
            description = rssChannel.description,
            id = rssChannel.id
        )
    }

}