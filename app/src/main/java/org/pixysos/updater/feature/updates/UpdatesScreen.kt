package org.pixysos.updater.feature.updates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun UpdatesScreen(
    modifier: Modifier = Modifier,
    viewModel: UpdatesViewModel = hiltViewModel()
) {
    val updatesUiState: UpdatesUiState by viewModel.uiState.collectAsStateWithLifecycle()
    when(updatesUiState) {
        is UpdatesUiState.Error -> {

        }
        is UpdatesUiState.Success -> {

        }
        else -> {

        }
    }
}

