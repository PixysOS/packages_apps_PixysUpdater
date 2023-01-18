package org.pixysos.updater.core.common.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val updaterDispatcher: UpdaterDispatchers)

enum class UpdaterDispatchers {
    IO
}