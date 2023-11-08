package org.pixysos.updater.feature.updates

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.pixysos.updater.R
import org.pixysos.updater.core.data.model.PixysBuildProperty
import org.pixysos.updater.core.designsystem.component.UpdaterTopAppBar
import org.pixysos.updater.core.designsystem.theme.GreenLooksGood

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UpdatesScreen(
    modifier: Modifier = Modifier,
    viewModel: UpdatesViewModel = hiltViewModel()
) {
    val updatesUiState: UpdatesUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val deviceInfoSummaryList = viewModel.deviceSummaryList
    val connection = rememberNestedScrollInteropConnection()

    Scaffold(
        topBar = {
            UpdaterTopAppBar(
                titleRes = R.string.system_updates,
                navigationIcon = {}
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ }, modifier = Modifier.windowInsetsPadding(
                    WindowInsets.navigationBars
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_sync),
                    contentDescription = stringResource(
                        R.string.check_for_updates_content_desc
                    ),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
                Text(
                    text = stringResource(R.string.check_updates),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .nestedScroll(connection = connection)
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            when (updatesUiState) {
                is UpdatesUiState.Error -> {

                }

                is UpdatesUiState.Success -> {
                    UpdateStatusCard(
                        title = stringResource(id = R.string.looks_good), summary = stringResource(
                            id = R.string.no_updates_available,
                        ),
                        updateFound = true
                    )
                }

                else -> {
                    UpdateStatusCard(
                        title = stringResource(id = R.string.looks_good),
                        summary = stringResource(id = R.string.checking_for_updates),
                        updateFound = false
                    )
                }
            }

            DeviceInformationSection(
                modifier = Modifier.padding(top = 24.dp),
                deviceInfoList = deviceInfoSummaryList.mapIndexed { i, summary ->
                    DeviceInformation(
                        title = deviceInfoTitles[i],
                        iconRes = deviceInfoIcons[i],
                        summary = summary
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UpdateStatusCard(
    modifier: Modifier = Modifier,
    title: String,
    summary: String,
    updateFound: Boolean
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 750, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "Infinite loading animation"
    )

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_verified_user),
                contentDescription = "Security update good",
                colorFilter = ColorFilter.tint(color = GreenLooksGood.copy(alpha = if (updateFound) 1f else alpha)),
                modifier = Modifier.size(40.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedContent(
                    targetState = title,
                    label = "Title change animation"
                ) { targetTitle ->
                    Text(
                        text = targetTitle,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                AnimatedContent(
                    targetState = summary,
                    label = "Summary change animation"
                ) { targetSummary ->
                    Text(
                        text = targetSummary,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.paddingFromBaseline(top = 12.dp),
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}


data class DeviceInformation(
    @StringRes val title: Int,
    @DrawableRes val iconRes: Int,
    val summary: String
)

val deviceInfoTitles = listOf(
    R.string.security_update,
    R.string.device,
    R.string.pixys_version
)

val deviceInfoIcons = listOf(
    R.drawable.ic_shield,
    R.drawable.ic_smartphone,
    R.drawable.ic_perm_device_information
)

@Composable
fun DeviceInformationSection(
    modifier: Modifier = Modifier,
    deviceInfoList: List<DeviceInformation>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 24.dp, start = 8.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.device_information),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(deviceInfoList) { deviceInfo ->
            DeviceInformationElement(
                modifier = Modifier.padding(vertical = 8.dp),
                titleRes = deviceInfo.title,
                summary = if (deviceInfo.title != R.string.security_update) deviceInfo.summary else stringResource(
                    id = R.string.update_from,
                    deviceInfo.summary
                ),
                iconRes = deviceInfo.iconRes
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
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Column(
            modifier = modifier.padding(start = 24.dp),
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
}
