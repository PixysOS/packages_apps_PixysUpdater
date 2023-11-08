package org.pixysos.updater.feature.updates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.pixysos.updater.core.data.model.PixysBuildProperty
import org.pixysos.updater.core.data.model.UpdatePackage
import org.pixysos.updater.core.data.repository.BuildPropertiesRepository
import org.pixysos.updater.core.data.repository.UpdatesRepository
import javax.inject.Inject

@HiltViewModel
class UpdatesViewModel @Inject constructor(
    private val updatesRepository: UpdatesRepository,
    private val buildPropertiesRepository: BuildPropertiesRepository
) : ViewModel() {
    val uiState: StateFlow<UpdatesUiState> = updatesRepository.getUpdatePackageInfo(
        device = buildPropertiesRepository.buildProperty.device!!,
        buildEdition = buildPropertiesRepository.buildProperty.buildEdition!!
    )
        .map<UpdatePackage, UpdatesUiState>(UpdatesUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UpdatesUiState.Loading
        )

    private val _buildProperty: MutableStateFlow<PixysBuildProperty> = MutableStateFlow(buildPropertiesRepository.buildProperty)
    val buildProperty: StateFlow<PixysBuildProperty>
        get() = _buildProperty.asStateFlow()

    val deviceSummaryList: List<String>
        get() = _buildProperty.value.let { property ->
            listOf(
                property.securityPatchDate!!,
                property.device!!,
                property.releaseType!!
            )
        }
}

sealed interface UpdatesUiState {
    object Loading : UpdatesUiState
    data class Success(val updatePackage: UpdatePackage) : UpdatesUiState
    data class Error(val error: Throwable? = null) : UpdatesUiState
}