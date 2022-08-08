package com.victorhvs.lotonaticos.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.victorhvs.lotonaticos.presentation.navigation.Screen.Companion.DRAW_ARGUMENT_KEY
import com.victorhvs.lotonaticos.presentation.screens.result_list.ContestResultListScreen
import com.victorhvs.lotonaticos.presentation.screens.splash_screen.AnimatedSplashScreen

@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Browse.route) {
//            BrowseScreen(navController = navController)
            ContestResultListScreen(navController = navController)
        }
        composable(route = Screen.Setting.route) {
//            BrowseScreen(navController = navController)
        }
        composable(route = Screen.Statistic.route) {
//            BrowseScreen(navController = navController)
        }
        composable(
            route = Screen.ResultList.route,
            arguments = listOf(navArgument(DRAW_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) {
        }
    }
}