package org.pixysos.updater.feature.updates

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.pixysos.updater.R
import org.pixysos.updater.core.designsystem.theme.UpdaterTheme

@Composable
fun AutomaticUpdatesSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(modifier = modifier) {
        AutomaticUpdatesButton(
            onClick = onClick,
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = stringResource(R.string.automatic_updates_desc),
            modifier = Modifier.paddingFromBaseline(
                top = 32.dp
            ),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutomaticUpdatesButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.automatic_updates), modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(
    name = "Dark",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun AutomaticUpdatesSectionPreview() {
    UpdaterTheme {
        Surface {
            AutomaticUpdatesSection(onClick = { /*TODO*/ }, checked = true, onCheckedChange = {})
        }
    }
}

@Preview
@Composable
fun AutomaticUpdatesButtonPreview() {
    AutomaticUpdatesButton(onClick = { /*TODO*/ }, checked = true, onCheckedChange = {})
}