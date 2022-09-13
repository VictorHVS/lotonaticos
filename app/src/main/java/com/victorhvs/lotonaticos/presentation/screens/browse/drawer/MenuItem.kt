package com.victorhvs.lotonaticos.presentation.screens.browse.drawer

import com.victorhvs.lotonaticos.R

data class MenuItem(
    val id: ScreensRoute,
    val textId: Int
)

val drawerScreens = listOf(
    MenuItem(ScreensRoute.SCREEN_1, R.string.app_name),
    MenuItem(ScreensRoute.SCREEN_2, R.string.app_name),
    MenuItem(ScreensRoute.SCREEN_3, R.string.app_name),
)

enum class ScreensRoute {
    SCREEN_1, SCREEN_2, SCREEN_3
}