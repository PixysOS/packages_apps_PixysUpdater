package org.pixysos.updater.feature.updates

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.pixysos.updater.R
import org.pixysos.updater.core.designsystem.component.UpdaterTopAppBar
import org.pixysos.updater.core.designsystem.theme.GreenLooksGood

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UpdatesScreen(
    modifier: Modifier = Modifier,
    viewModel: UpdatesViewModel = hiltViewModel()
) {
    val updatesUiState: UpdatesUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            UpdaterTopAppBar(
                titleRes = R.string.system_updates,
                navigationIcon = {}
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.windowInsetsPadding(
                WindowInsets.navigationBars)) {
                Image(painter = painterResource(id = R.drawable.ic_sync), contentDescription = stringResource(
                    R.string.check_for_updates_content_desc
                ),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
                Text(text = stringResource(R.string.check_updates), style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(start = 16.dp))
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            when (updatesUiState) {
                is UpdatesUiState.Error -> {

                }
                is UpdatesUiState.Success -> {
                    NoUpdatesAvailable()
                }
                else -> {
                    CheckingForUpdates()
                }
            }

            DeviceInformationSection(
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}

@Composable
fun CheckingForUpdates(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
    ) {
        Row (modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_security_update_good),
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(12.dp)
                    .size(32.dp),
                contentDescription = "Security update good",
                colorFilter = ColorFilter.tint(color = GreenLooksGood)
            )
            Column (
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.looks_good), style = MaterialTheme.typography.headlineSmall)
                Text(text = stringResource(R.string.checking_for_updates), style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.paddingFromBaseline(top = 12.dp), color = MaterialTheme.colorScheme.outline)
            }
        }
    }
}


@Composable
fun NoUpdatesAvailable(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
    ) {
            Row (modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_security_update_good),
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(12.dp)
                        .size(32.dp),
                    contentDescription = "Security update good",
                    colorFilter = ColorFilter.tint(color = GreenLooksGood)
                )
                Column (
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(id = R.string.looks_good), style = MaterialTheme.typography.headlineSmall)
                    Text(text = stringResource(id = R.string.no_updates_available), style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.paddingFromBaseline(top = 12.dp), color = MaterialTheme.colorScheme.outline)
                }
            }
    }
}

data class StatusInfo(@StringRes val title: Int, @DrawableRes val iconRes: Int, val summary: String)
val informationItems: List<StatusInfo> = listOf(
    StatusInfo(title = R.string.security_update, R.drawable.ic_shield, "Update from October 05, 2023"),
    StatusInfo(title = R.string.device, R.drawable.ic_smartphone, "Pixel 7"),
    StatusInfo(title = R.string.pixys_version, R.drawable.ic_perm_device_information, "7.x.x"),
)

@Composable
fun DeviceInformationSection(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 24.dp, start = 8.dp)
    ) {
        item {
            Text(text = "Device Information", modifier = Modifier.padding(bottom = 24.dp))
        }
        items(informationItems) { statusInfo ->
            DeviceInformationElement(
                modifier = Modifier.padding(vertical = 4.dp),
                titleRes = statusInfo.title,
                summary = statusInfo.summary,
                iconRes = statusInfo.iconRes
            )
        }
    }
}

@Composable
fun DeviceInformationElement(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    @DrawableRes iconRes: Int,
    summary: String,
    contentDescription: String? = null
) {
    Row (verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = iconRes), contentDescription = contentDescription, colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary))
        Column(modifier = modifier.padding(start = 24.dp), verticalArrangement = Arrangement.Center) {
            Text(stringResource(id = titleRes), style = MaterialTheme.typography.titleLarge)
            Text(
                summary,
                modifier = modifier.paddingFromBaseline(top = 12.dp),
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}
