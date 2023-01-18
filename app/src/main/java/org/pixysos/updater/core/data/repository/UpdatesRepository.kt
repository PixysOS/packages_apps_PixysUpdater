package org.pixysos.updater.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.pixysos.updater.core.data.model.UpdatePackage

/**
 * Data layer implementation for [UpdatePackage]
 */
interface UpdatesRepository {
    /**
     * Returns the build information of latest update.
     */
    fun getUpdatePackageInfo(device: String, buildEdition: String): Flow<UpdatePackage>
}