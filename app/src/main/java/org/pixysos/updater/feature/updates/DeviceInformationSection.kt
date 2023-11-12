package org.pixysos.updater.feature.updates

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.pixysos.updater.R

@Composable
fun DeviceInformationElement(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    summary: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(id = titleRes), style = MaterialTheme.typography.titleLarge)
        Text(
            summary,
            modifier = Modifier.paddingFromBaseline(top = 16.dp),
            color = MaterialTheme.colorScheme.outline
        )
    }
}

data class DeviceInformation(
    @StringRes val title: Int,
    val summary: String
)

val deviceInfoTitles = listOf(
    R.string.security_update,
    R.string.device,
    R.string.pixys_version,
)
