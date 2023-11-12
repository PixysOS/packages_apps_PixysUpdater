package org.pixysos.updater.feature.updates

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.pixysos.updater.R
import org.pixysos.updater.core.designsystem.component.UpdaterTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UpdatesScreen(
    modifier: Modifier = Modifier,
    viewModel: UpdatesViewModel = hiltViewModel()
) {
    val updatesUiState: UpdatesUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val deviceInfoSummaryList = viewModel.deviceSummaryList
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState())
    var checked by rememberSaveable {
        mutableStateOf(true)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            UpdaterTopAppBar(
                titleRes = R.string.system_updates,
                navigationIcon = {},
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            contentPadding = innerPadding
        ) {
            item {
                AutomaticUpdatesSection(
                    modifier = Modifier.padding(vertical = 24.dp),
                    onClick = { checked = !checked },
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    })
            }

            item {
                when (updatesUiState) {
                    is UpdatesUiState.Error -> {

                    }

                    is UpdatesUiState.Success -> {
                        UpdatesSection(
                            title = stringResource(id = R.string.looks_good),
                            summary = stringResource(
                                id = R.string.no_updates_available,
                            ),
                            isCheckingForUpdates = false,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    else -> {
                        UpdatesSection(
                            title = stringResource(id = R.string.looks_good),
                            summary = stringResource(id = R.string.checking_for_updates),
                            isCheckingForUpdates = true,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
            }

            items(deviceInfoSummaryList.mapIndexed { i, summary ->
                DeviceInformation(
                    title = deviceInfoTitles[i],
                    summary = summary
                )
            }) { deviceInfo ->
                DeviceInformationElement(
                    modifier = Modifier.padding(vertical = 8.dp),
                    titleRes = deviceInfo.title,
                    summary = if (deviceInfo.title != R.string.security_update) deviceInfo.summary else stringResource(
                        id = R.string.update_from,
                        deviceInfo.summary
                    ),
                )
            }
        }
    }
}

@Composable
fun UpdatesSection(
    modifier: Modifier = Modifier,
    title: String,
    summary: String,
    isCheckingForUpdates: Boolean = true
) {
    Column(modifier = modifier) {
        UpdateStatusCard(
            title = title,
            summary = summary,
            isCheckingForUpdates = isCheckingForUpdates
        )
        Text(
            text = "Last checked - 20 hours ago",
            modifier = Modifier.paddingFromBaseline(top = 32.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UpdateStatusCard(
    modifier: Modifier = Modifier,
    title: String,
    summary: String,
    isCheckingForUpdates: Boolean
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
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary.copy(alpha = if (!isCheckingForUpdates) 1f else alpha)),
                modifier = Modifier.size(40.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.paddingFromBaseline(top = 12.dp),
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }

        Button(
            shape = MaterialTheme.shapes.medium,
            contentPadding = PaddingValues(vertical = 16.dp),
            enabled = !isCheckingForUpdates,
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.check_updates))
        }
    }
}
