package org.pixysos.updater.core.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.pixysos.updater.core.common.network.di.Dispatcher
import org.pixysos.updater.core.common.network.di.UpdaterDispatchers.IO
import org.pixysos.updater.core.data.model.UpdatePackage
import org.pixysos.updater.core.network.retrofit.RetrofitUpdaterNetwork
import javax.inject.Inject

class UpdatesRepositoryImpl @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val retrofitUpdaterNetwork: RetrofitUpdaterNetwork
) : UpdatesRepository {

    override fun getUpdatePackageInfo(
        device: String,
        buildEdition: String
    ): Flow<UpdatePackage> =
        flow {
            emit(
                retrofitUpdaterNetwork.getUpdatePackageInfo(
                    device = "X00TD",
                    buildEdition = "vanilla"
                )
            )
        }.flowOn(ioDispatcher)
}