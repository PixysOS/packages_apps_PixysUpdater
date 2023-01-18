package org.pixysos.updater.core.network.retrofit

import org.pixysos.updater.BuildConfig
import org.pixysos.updater.core.network.UpdaterNetworkDataSource
import org.pixysos.updater.core.data.model.UpdatePackage
import retrofit2.http.GET

private interface RetrofitUpdaterNetworkApi {
//    @GET()
}

private const val UpdaterBaseUrl = BuildConfig.BASE_URL
private const val UpdaterOtaEndpoint = UpdaterBaseUrl + BuildConfig.OTA_UPDATE_ENDPOINT

class RetrofitUpdaterNetwork: UpdaterNetworkDataSource {

    override suspend fun getUpdatePackageInfo(device: String, buildEdition: String): UpdatePackage {
        TODO("Not yet implemented")
    }
}