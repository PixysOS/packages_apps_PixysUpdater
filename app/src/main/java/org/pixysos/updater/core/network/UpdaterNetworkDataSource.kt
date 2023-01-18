package org.pixysos.updater.core.network

import org.pixysos.updater.data.model.UpdatePackage

/**
 * Interface representing network calls to Updater backend
 */
interface UpdaterNetworkDataSource {
    suspend fun getUpdatePackageInfo(device: String, buildEdition: String): UpdatePackage
}
