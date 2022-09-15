package com.victorhvs.lotonaticos.presentation.screens.browse.drawer

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.DeveloperMode
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.IconCompat.IconType
import com.victorhvs.lotonaticos.BuildConfig
import com.victorhvs.lotonaticos.R
import kotlinx.coroutines.launch

@Composable
fun DrawerSheetComponent(
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val headerItems = listOf(
        DrawerHeaderEnum.ROADMAP,
        DrawerHeaderEnum.RATE_US,
        DrawerHeaderEnum.NEED_HELP
    )

    val footerItems = listOf(
        DrawerFooterEnum.SUGGESTIONS,
        DrawerFooterEnum.PRIVACY_POLICY,
        DrawerFooterEnum.TERMS_OF_SERVICE
    )

    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))

        NavigationDrawerItem(
            badge = {
                Image(
                    painter = painterResource(id = R.drawable.clover),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .size(24.dp),
                    alignment = Alignment.Center
                )
            },
            label = {
                val globalText = stringResource(id = R.string.app_name)
                val spanStyles = listOf(
                    AnnotatedString.Range(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        start = 0,
                        end = 4
                    )
                )
                Text(
                    text = AnnotatedString(globalText, spanStyles),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            selected = false,
            onClick = {
                scope.launch { drawerState.close() }
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        headerItems.forEach { item ->
            val labelText = stringResource(id = item.label)
            val url = stringResource(id = item.url)

            NavigationDrawerItem(
                icon = { Icon(item.icon, contentDescription = labelText) },
                label = {
                    Text(
                        text = labelText,
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                selected = false,
                onClick = {
                    openUrl(url = url, context = context)
                    scope.launch { drawerState.close() }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        footerItems.forEach { item ->
            val labelText = stringResource(id = item.label)
            val url = stringResource(id = item.url)
            NavigationDrawerItem(
                label = {
                    Text(
                        labelText,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                selected = false,
                onClick = {
                    openUrl(url = url, context = context)
                    scope.launch { drawerState.close() }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, bottom = 24.dp),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.labelMedium,
            text = "v${BuildConfig.VERSION_NAME}"
        )
    }
}

fun openUrl(url: String, context: Context) {
    context.startActivity(
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
    )
}

enum class DrawerHeaderEnum(
    val icon: ImageVector,
    @StringRes val label: Int,
    @StringRes val url: Int
) {
    ROADMAP(
        icon = Icons.Outlined.DeveloperMode,
        label = R.string.drawer_header_roadmap,
        url = R.string.drawer_header_roadmap_url
    ),
    RATE_US(
        icon = Icons.Filled.StarRate,
        label = R.string.drawer_header_rate_us,
        url = R.string.drawer_header_rate_us_url
    ),
    NEED_HELP(
        icon = Icons.Outlined.HelpOutline,
        label = R.string.drawer_header_need_help,
        url = R.string.drawer_header_need_help_url
    )
}

enum class DrawerFooterEnum(
    @StringRes val label: Int,
    @StringRes val url: Int
) {
    SUGGESTIONS(
        label = R.string.drawer_footer_suggestions,
        url = R.string.drawer_footer_suggestions_url
    ),
    TERMS_OF_SERVICE(
        label = R.string.drawer_footer_terms_of_service,
        url = R.string.drawer_footer_terms_of_service_url
    ),
    PRIVACY_POLICY(
        label = R.string.drawer_footer_privacy_policy,
        url = R.string.drawer_footer_privacy_policy_url
    )
}
