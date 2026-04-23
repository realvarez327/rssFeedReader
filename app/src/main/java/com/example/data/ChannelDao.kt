package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelDao{
    @Query("SELECT * FROM channels where url=:urlGiven")
    suspend fun getChannelByUrl(urlGiven:String): List<ChannelEntity>

    @Query("SELECT * FROM channels")
    suspend fun getAllChannels(): List<ChannelEntity>

    @Insert
    suspend fun addChannel(feedToAdd: ChannelEntity)


    @Query("DELETE FROM channels WHERE id=:idGiven")
    suspend fun removeChannelById(idGiven:Int)

    @Query("DELETE FROM channels WHERE url=:urlGiven")
    suspend fun removeChannelByUrl(urlGiven:String)

    @Query("SELECT * FROM channels ORDER BY channelName DESC")
    suspend fun observeChannels(): List<ChannelEntity>
}