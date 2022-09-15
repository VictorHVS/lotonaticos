package com.victorhvs.lotonaticos.presentation.screens.browse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.victorhvs.lotonaticos.presentation.screens.browse.drawer.DrawerSheetComponent
import com.victorhvs.lotonaticos.presentation.screens.browse.drawer.DrawerTopBar
import com.victorhvs.lotonaticos.presentation.screens.resultList.ContestResultListScreen
import kotlinx.coroutines.launch

@Composable
fun BrowseScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerSheetComponent(drawerState) },
        content = {
            Scaffold(
                modifier = Modifier.background(MaterialTheme.colorScheme.inversePrimary),
                topBar = {
                    DrawerTopBar(openDrawer = {
                        scope.launch { drawerState.open() }
                    })
                },
                content = {
                    ContestResultListScreen(Modifier.padding(it))
                }
            )
        }
    )
}
