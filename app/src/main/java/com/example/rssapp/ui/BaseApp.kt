package com.example.rssapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rssapp.R

enum class AppScreenNames(@StringRes val id:Int){
    FeedScreen(R.string.feed_screen),
    PreferencesScreen(R.string.preferences_screen)
}

@Composable
fun BaseApp(modifier: Modifier, viewModel: RssViewModel){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreenNames.valueOf(
        backStackEntry?.destination?.route ?: AppScreenNames.FeedScreen.name
    )

    Scaffold(bottomBar = {
        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
            val currScreen =  backStackEntry?.destination?.route
            NavigationBarItem(
                selected = (currScreen== AppScreenNames.FeedScreen.name),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = stringResource(R.string.feed_screen)
                    )
                },
                onClick = {
                    navController.navigate(AppScreenNames.FeedScreen.name)
                })
            NavigationBarItem(
                selected = (currScreen== AppScreenNames.PreferencesScreen.name),
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = stringResource(R.string.preferences_screen)
                    )
                },
                onClick = {
                    navController.navigate(AppScreenNames.PreferencesScreen.name)
                }
            )
        }


    }) {innerPadding->
        NavHost(
            navController,
            startDestination = AppScreenNames.FeedScreen
        ){
            composable (route = AppScreenNames.FeedScreen.name){
                FeedScreen(modifier = Modifier.padding(innerPadding),viewModel = viewModel)
            }
            composable (route = AppScreenNames.PreferencesScreen.name){
                PreferencesScreen(modifier = Modifier.padding(innerPadding), viewModel = viewModel)
            }
        }
    }

}