package com.example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channels")
data class ChannelEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "url") val url:String,
    @ColumnInfo(name="channelName") val channelName:String,
    @ColumnInfo(name = "description") val description:String
)
