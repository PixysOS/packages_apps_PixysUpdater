package org.pixysos.updater.core.data.repository

import org.pixysos.updater.core.data.model.PixysBuildProperty

/**
 * Data layer implementation for [PixysBuildProperty]
 */
interface BuildPropertiesRepository {
    val buildProperty: PixysBuildProperty

    /**
     * Returns the device codename.
     */
    fun getDeviceCodename(): String?

    /**
     * Returns the release type. Can be either "official" or "unofficial".
     */
    fun getReleaseType(): String?

    /**
     * Returns the build variant. Can be either "vanilla" or "gapps".
     */
    fun getBuildEdition(): String?

    /**
     * Returns the security patch date.
     */
    fun getSecurityPatchDate(): String?
}