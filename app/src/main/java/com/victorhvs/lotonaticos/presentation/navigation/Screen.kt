package com.victorhvs.lotonaticos.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StackedLineChart
import androidx.compose.ui.graphics.vector.ImageVector
import com.victorhvs.lotonaticos.R

sealed class Screen(val route: String) {

    object Browse : Screen("home/results")
    object Setting : Screen("home/settings")
    object Statistic : Screen("home/analytics")
    object ResultList : Screen("result_list_screen/{drawId}") {
        fun passDrawId(drawId: String): String {
            return "result_list_screen/$drawId"
        }
    }
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    RESULT(R.string.home_results, Icons.Filled.Home, "home/results"),
    STATS(R.string.home_analytics, Icons.Filled.StackedLineChart, "home/analytics"),
    SETTING(R.string.home_settings, Icons.Filled.Settings, "home/settings")
}
