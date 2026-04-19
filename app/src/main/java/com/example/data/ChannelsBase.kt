package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ChannelEntity::class], version = 1)
abstract class ChannelsBase: RoomDatabase(){
    abstract fun channelsDao(): ChannelDao

    companion object {
        @Volatile
        private var INSTANCE: ChannelsBase? = null

        fun getDatabase(context: Context): ChannelsBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChannelsBase::class.java,
                    "health_database"//database file name
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}