package org.flynndevs.com

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.flynndevs.com.feature.home.HomeScreen
import org.flynndevs.com.navigation.MainNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainNavigation()
    }
}