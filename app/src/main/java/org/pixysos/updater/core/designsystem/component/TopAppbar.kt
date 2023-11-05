package org.pixysos.updater.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.pixysos.updater.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdaterTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    navigationIcon: @Composable () -> Unit,
    navigationIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(),
) {
    LargeTopAppBar(
        title = { Text(text = stringResource(id = titleRes)) },
        navigationIcon = navigationIcon,
        colors = colors,
        modifier = modifier,
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun UpdaterTopAppBarPreview() {
    UpdaterTopAppBar(
        titleRes = R.string.app_name,
        navigationIcon = { },
        navigationIconContentDescription = "Navigation Icon"
    )
}