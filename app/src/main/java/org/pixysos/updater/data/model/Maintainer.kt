package org.pixysos.updater.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Maintainer(
    val name: String,
    @SerialName(value = "url")
    val siteUrl: String
)
