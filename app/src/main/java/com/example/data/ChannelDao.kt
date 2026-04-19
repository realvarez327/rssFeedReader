package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelDao{
    @Query("SELECT url FROM channels")
    suspend fun getChannelByUrl(url:String): List<ChannelEntity>

    @Query("SELECT * FROM channels")
    suspend fun getAllChannels(): List<ChannelEntity>

    @Insert
    suspend fun addChannel(feedToAdd: ChannelEntity)

    @Delete
    suspend fun removeChannelById(id:Int)

    @Query("DELETE FROM channels WHERE url=:urlGiven")
    suspend fun removeChannelByUrl(urlGiven:String)

    @Query("SELECT * FROM channels ORDER BY channelName DESC")
    suspend fun observeChannels(): Flow<List<ChannelEntity>>
}