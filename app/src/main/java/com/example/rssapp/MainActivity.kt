package com.example.rssapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.data.ChannelsBase
import com.example.data.RoomConverters
import com.example.data.RssParser
import com.example.data.RssRepository
import com.example.rssapp.network.NetworkObject
import com.example.rssapp.ui.BaseApp
import com.example.rssapp.ui.RssViewModel
import com.example.rssapp.ui.RssViewModelFactory
import com.example.rssapp.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val repo by lazy {
        val db = ChannelsBase.getDatabase(applicationContext)
        RssRepository(
            service = NetworkObject.rssApiService,
            channelDao = db.channelsDao(),
            feedParser = RssParser(),
            converters = RoomConverters()
        )
    }

    private val vm by viewModels<RssViewModel>{
        RssViewModelFactory(repo)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BaseApp(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = vm
                    )
                }
            }
        }
    }
}

