package com.victorhvs.lotonaticos.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.victorhvs.lotonaticos.presentation.screens.resultList.ContestResultListScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Browse.route
    ) {
        composable(route = Screen.Browse.route) {
            ContestResultListScreen()
        }
    }
}
