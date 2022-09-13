package com.victorhvs.lotonaticos.presentation.screens.browse.drawer

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerBody(
    menuItems: List<MenuItem>,
    scaffoldState: DrawerState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(menuItems) { item ->
            DrawerItem(
                item,
                modifier = modifier
            ) {
                scope.launch {
                    scaffoldState.close()
                }
                onItemClick(item)
            }
        }
    }
}