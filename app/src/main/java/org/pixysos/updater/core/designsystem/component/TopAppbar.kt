package org.pixysos.updater.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.pixysos.updater.R
import org.pixysos.updater.core.designsystem.theme.UpdaterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdaterTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    navigationIcon: @Composable () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    LargeTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleRes),
            )
        },
        navigationIcon = navigationIcon,
        modifier = modifier,
        scrollBehavior = scrollBehavior,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun UpdaterTopAppBarPreview() {
    UpdaterTheme {
        UpdaterTopAppBar(
            titleRes = R.string.system_updates,
            navigationIcon = { },
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        )
    }
}