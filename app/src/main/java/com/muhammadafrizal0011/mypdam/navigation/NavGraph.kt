package com.muhammadafrizal0011.mypdam.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muhammadafrizal0011.mypdam.ui.theme.screen.AboutScreen
import com.muhammadafrizal0011.mypdam.ui.theme.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable (route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen()
        }
    }
}
