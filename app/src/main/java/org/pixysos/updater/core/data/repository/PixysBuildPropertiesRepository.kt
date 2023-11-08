package org.pixysos.updater.core.data.repository

import android.os.SystemProperties
import org.pixysos.updater.BuildConfig
import org.pixysos.updater.core.data.model.PixysBuildProperty
import java.text.SimpleDateFormat
import java.util.Locale
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
            buildEdition = getBuildEdition(),
            securityPatchDate = getSecurityPatchDate()
        )

    override fun getDeviceCodename(): String {
        val device = SystemProperties.get(BuildConfig.PROP_VENDOR_DEVICE, "N/A")
        val model = SystemProperties.get(BuildConfig.PROP_VENDOR_MODEL, "N/A")

        if (device == "N/A" || model == "N/A") {
            return "N/A"
        }

        return "$model ($device)"
    }

    override fun getReleaseType(): String? =
        SystemProperties.get(BuildConfig.PROP_RELEASE_TYPE, "unofficial")

    override fun getBuildEdition(): String? =
        SystemProperties.get(BuildConfig.PROP_BUILD_EDITION, "N/A")

    override fun getSecurityPatchDate(): String? {
        val securityPatchDateString =
            SystemProperties.get(BuildConfig.PROP_SECURITY_PATCH_LEVEL, "N/A")
        val securityPatchDate =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(securityPatchDateString)
        return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(securityPatchDate)
    }
}