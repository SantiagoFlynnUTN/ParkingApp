package org.flynndevs.com.feature.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Platform-specific map component that displays a map and user location
 */
@Composable
expect fun MapComponent(
    latitude: Double?,
    longitude: Double?,
    onLocationUpdate: (Double, Double) -> Unit,
    modifier: Modifier = Modifier
)