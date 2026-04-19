package com.example.rssapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class AppScreenNames(){
    FeedScreen,
    PreferencesScreen
}

@Composable
fun BaseApp(modifier: Modifier){
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = AppScreenNames.FeedScreen
    ){
        composable (route = AppScreenNames.FeedScreen.name){

        }
        composable (route = AppScreenNames.PreferencesScreen.name){

        }
    }
}