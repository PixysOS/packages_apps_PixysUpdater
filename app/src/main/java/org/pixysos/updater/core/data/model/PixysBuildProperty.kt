package org.pixysos.updater.core.data.model

/**
 * Model class for holding build properties of a device.
 */
data class PixysBuildProperty(
    val device: String? = null,
    val releaseType: String? = null,
    val buildEdition: String? = null,
    val securityPatchDate: String? = null
)
