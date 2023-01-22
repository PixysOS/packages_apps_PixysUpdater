package org.pixysos.updater.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.pixysos.updater.core.data.repository.BuildPropertiesRepository
import org.pixysos.updater.core.data.repository.PixysBuildPropertiesRepository
import org.pixysos.updater.core.data.repository.UpdatesRepository
import org.pixysos.updater.core.data.repository.UpdatesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsUpdatesRepository(
        updatesRepositoryImpl: UpdatesRepositoryImpl
    ): UpdatesRepository

    @Binds
    fun bindsBuildPropertiesRepository(
        pixysBuildPropertiesRepository: PixysBuildPropertiesRepository
    ): BuildPropertiesRepository
}