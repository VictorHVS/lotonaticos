package com.victorhvs.lotonaticos.presentation.screens.browse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.victorhvs.lotonaticos.domain.State
import com.victorhvs.lotonaticos.domain.models.Lottery
import com.victorhvs.lotonaticos.presentation.navigation.HomeSections
import kotlinx.coroutines.launch

@Composable
fun BrowseScreen(
    navController: NavController,
    browseViewModel: BrowseViewModel = hiltViewModel()
) {
    val state = browseViewModel.selectedBrewery.collectAsState(initial = State.loading()).value
//    BrowseContainer(state, navController)

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        badge = { Text(text = "AEHO") },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                Spacer(Modifier.height(20.dp))
                Button(onClick = { scope.launch { drawerState.open() } }) {
                    Text("Click to open")
                }
            }
            BrowseContainer(state, navController)
        }
    )
}

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

    val navBackStackEntry = navController.currentBackStackEntryAsState().value
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
