package org.pixysos.updater.feature.updates

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.pixysos.updater.R
import org.pixysos.updater.core.designsystem.component.UpdaterTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UpdatesScreen(
    modifier: Modifier = Modifier,
    viewModel: UpdatesViewModel = hiltViewModel()
) {
    val updatesUiState: UpdatesUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            UpdaterTopAppBar(
                titleRes = R.string.app_name,
                navigationIcon = Icons.Default.ArrowBack,
                navigationIconContentDescription = stringResource(id = R.string.navigation_icon_content_description)
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) { padding ->
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            when (updatesUiState) {
                is UpdatesUiState.Error -> {

                }
                is UpdatesUiState.Success -> {

                }
                else -> {

                }
            }
        }
    }
}

