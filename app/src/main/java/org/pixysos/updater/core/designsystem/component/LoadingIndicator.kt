package org.pixysos.updater.core.designsystem.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UpdaterLinearProgressIndicator(
    modifier: Modifier = Modifier,
) {
    LinearProgressIndicator(
        progress = .75f,
        strokeCap = StrokeCap.Round,
        trackColor = MaterialTheme.colorScheme.onBackground,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .height(16.dp)
            .clip(CircleShape)
    )
}

@Composable
fun UpdaterCircularProgressIndicator(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        progress = .75f,
        strokeWidth = 6.dp,
        strokeCap = StrokeCap.Round,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview
@Composable
fun UpdaterLinearProgressIndicatorPreview() {
    UpdaterLinearProgressIndicator()
}

@Preview
@Composable
fun UpdaterCircularProgressIndicatorPreview() {
    UpdaterCircularProgressIndicator()
}