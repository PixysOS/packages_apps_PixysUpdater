package org.pixysos.updater.core.data.repository

import android.os.SystemProperties
import org.pixysos.updater.BuildConfig
import org.pixysos.updater.core.data.model.PixysBuildProperty
import javax.inject.Inject

/**
 * [BuildPropertiesRepository] implementation for PixysOS.
 */
class PixysBuildPropertiesRepository @Inject constructor(

) : BuildPropertiesRepository {
    override val buildProperty: PixysBuildProperty
        get() = PixysBuildProperty(
            device = getDeviceCodename(),
            releaseType = getReleaseType(),
            buildEdition = getBuildEdition()
        )

    override fun getDeviceCodename(): String? =
        SystemProperties.get(BuildConfig.PROP_DEVICE, "N/A")

    override fun getReleaseType(): String? =
        SystemProperties.get(BuildConfig.PROP_RELEASE_TYPE, "unofficial")

    override fun getBuildEdition(): String? =
        SystemProperties.get(BuildConfig.PROP_BUILD_EDITION, "N/A")
}