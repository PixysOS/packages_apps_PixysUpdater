package org.pixysos.updater.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.pixysos.updater.core.data.model.UpdatePackage
import javax.inject.Inject

class UpdatesRepositoryImpl @Inject constructor() : UpdatesRepository {

    override fun getUpdatePackageInfo(device: String, buildEdition: String): Flow<UpdatePackage> =
        flow {

        }
}