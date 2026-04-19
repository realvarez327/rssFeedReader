package com.example.rssapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.ChannelsBase
import com.example.data.RoomConverters
import com.example.data.RssParser
import com.example.data.RssRepository
import com.example.rssapp.network.NetworkObject


enum class AppScreenNames(){
    FeedScreen,
    PreferencesScreen
}

@Composable
fun BaseApp(modifier: Modifier){
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val db = ChannelsBase.getDatabase(context)
    val repository = RssRepository(
        service = NetworkObject.rssApiService,
        channelDao = db.channelsDao(),
        feedParser = RssParser(),
        converters = RoomConverters()
    )
    val vmFactory = RssViewModelFactory(
        repository = repository
    )

    val viewModel: RssViewModel = viewModel(factory = vmFactory)
    NavHost(
        navController,
        startDestination = AppScreenNames.FeedScreen
    ){
        composable (route = AppScreenNames.FeedScreen.name){
            FeedScreen(viewModel = viewModel)
        }
        composable (route = AppScreenNames.PreferencesScreen.name){

        }
    }
}