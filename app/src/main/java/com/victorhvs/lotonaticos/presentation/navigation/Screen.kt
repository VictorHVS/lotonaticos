package com.victorhvs.lotonaticos.presentation.navigation

sealed class Screen(val route: String) {

    companion object {
        const val DRAW_ARGUMENT_KEY = "drawId"
    }

    object Splash : Screen("splash_screen")
    object Browse : Screen("browse_screen")
    object Setting : Screen("setting_screen")
    object Statistic : Screen("statistic_screen")
    object ResultList : Screen("result_list_screen/{drawId}") {
        fun passDrawId(drawId: String): String {
            return "result_list_screen/$drawId"
        }
    }
}