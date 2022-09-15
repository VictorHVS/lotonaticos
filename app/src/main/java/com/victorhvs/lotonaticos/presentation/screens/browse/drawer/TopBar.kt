package com.victorhvs.lotonaticos.presentation.screens.browse.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.victorhvs.lotonaticos.R

@Composable
fun DrawerTopBar(
    openDrawer: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            val globalText = stringResource(id = R.string.app_name)
            val spanStyles = listOf(
                AnnotatedString.Range(
                    SpanStyle(fontWeight = FontWeight.Bold),
                    start = 0,
                    end = 4
                )
            )
            Text(text = AnnotatedString(globalText, spanStyles), style = MaterialTheme.typography.titleLarge)
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
    )
}