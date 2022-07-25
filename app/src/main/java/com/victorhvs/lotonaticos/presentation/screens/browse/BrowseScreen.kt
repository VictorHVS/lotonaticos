package com.victorhvs.lotonaticos.presentation.screens.browse

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.Lottery
import com.victorhvs.lotonaticos.presentation.navigation.HomeSections
import com.victorhvs.lotonaticos.R

@Composable
fun BrowseScreen(
    navController: NavController,
    browseViewModel: BrowseViewModel = hiltViewModel()
) {
    val state = browseViewModel.selectedBrewery.collectAsState(initial = State.loading()).value
    BrowseContainer(state, navController)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseContainer(state: State<List<Lottery>>, navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNav(navController = navController)
        },
        content = {
            when (state) {
                is State.Failed -> {
                    Text(
                        modifier = Modifier.padding(it),
                        text = state.message
                    )
                }
                is State.Loading -> {
                    Text(
                        modifier = Modifier.padding(it),
                        text = "LOADING"
                    )
                }
                is State.Success -> {
                    Text(
                        modifier = Modifier.padding(it),
                        text = state.data.map { it.title }.toString()
                    )
                }
            }
        }
    )
}

@Composable
fun BottomNav(
    navController: NavController
) {
    val screens = listOf(
        HomeSections.RESULT,
        HomeSections.STATS,
        HomeSections.SETTING
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val curreDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = curreDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: HomeSections,
    currentDestination: NavDestination?,
    navController: NavController
) {
    NavigationBarItem(
        label = {
            Text(text = stringResource(id = screen.title))
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "ICON")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}