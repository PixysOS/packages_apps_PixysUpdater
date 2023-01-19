package org.pixysos.updater.feature.updates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.pixysos.updater.core.data.model.UpdatePackage
import org.pixysos.updater.core.data.repository.UpdatesRepository
import javax.inject.Inject

@HiltViewModel
class UpdatesViewModel @Inject constructor(
    private val updatesRepository: UpdatesRepository
) : ViewModel() {
//    val uiState: StateFlow<UpdatesUiState> = updatesRepository.getUpdatePackageInfo()
//        .map<UpdatePackage, UpdatesUiState>(UpdatesUiState::Success)
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = UpdatesUiState.Loading
//        )
}

sealed interface UpdatesUiState {
    object Loading : UpdatesUiState
    data class Success(val updatePackage: UpdatePackage) : UpdatesUiState
    data class Error(val error: Throwable? = null) : UpdatesUiState
}