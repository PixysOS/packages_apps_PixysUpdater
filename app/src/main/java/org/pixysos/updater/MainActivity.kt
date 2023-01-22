package org.pixysos.updater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import org.pixysos.updater.core.designsystem.theme.UpdaterTheme
import org.pixysos.updater.feature.updates.UpdatesScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Turn off decor fitting system windows.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            val darkTheme = isSystemInDarkTheme()

            DisposableEffect(key1 = systemUiController, key2 = darkTheme) {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = !darkTheme
                )
                onDispose { }
            }

            UpdaterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UpdatesScreen()
                }
            }
        }
    }
}
