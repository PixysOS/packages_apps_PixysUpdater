package org.pixysos.updater.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePackage(
    val device: String,
    @SerialName(value = "edition")
    val buildEdition: String,
    @SerialName(value = "base")
    val versionName: String,
    @SerialName(value = "filename")
    val fileName: String,
    @SerialName(value = "version")
    val packageVersion: String,
    val timestamp: Long,
    @SerialName(value = "filehash")
    val fileHash: String,
    @SerialName(value = "filesize")
    val size: Long,
    @SerialName(value = "filepath")
    val path: String,
    @SerialName(value = "release")
    val releaseType: String,
    @SerialName(value = "deprecated")
    val isDeprecated: Boolean,
    @SerialName(value = "published")
    val isPublished: Boolean,
    val changelogs: String,
    @SerialName(value = "downloads")
    val downloadsCount: Int,
    @SerialName(value = "url")
    val downloadUrl: String,
    val maintainers: List<Maintainer>,
    @SerialName(value = "xda_thread")
    val forumThreadUrl: String,
    @SerialName(value = "donate_url")
    val donationUrl: String,
)
