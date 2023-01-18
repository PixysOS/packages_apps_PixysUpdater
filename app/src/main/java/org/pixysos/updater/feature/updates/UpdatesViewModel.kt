package org.pixysos.updater.feature.updates

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.pixysos.updater.core.data.model.UpdatePackage
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

}

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val updatePackage: UpdatePackage) : HomeUiState
    data class Error(val error: Throwable? = null) : HomeUiState
}