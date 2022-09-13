package com.victorhvs.lotonaticos.presentation.screens.browse.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    titleResId: Int,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = titleResId))
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        openDrawer()
                    },
                contentDescription = null
            )
        },
        modifier = modifier
    )
}