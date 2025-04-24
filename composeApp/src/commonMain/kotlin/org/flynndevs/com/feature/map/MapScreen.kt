package org.flynndevs.com.feature.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MapScreen(
    viewModel: MapViewModel = koinViewModel(),
    navController: NavController? = null
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onIntent(MapViewIntent.RequestLocationPermission)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.displaySmall
        )

        MapComponent(
            latitude = state.userLatitude,
            longitude = state.userLongitude,
            onLocationUpdate = { lat, lng ->
                viewModel.onIntent(MapViewIntent.UpdateUserLocation(lat, lng))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(vertical = 16.dp)
        )

        state.userLatitude?.let { lat ->
            state.userLongitude?.let { lng ->
                Text(
                    text = "Current location: $lat, $lng",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Button(
            onClick = {
                viewModel.onIntent(MapViewIntent.GetUserLocation)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Get My Location")
        }

        Button(
            onClick = {
                viewModel.onIntent(MapViewIntent.UpdateTitle("Updated Map View"))
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Update Title")
        }

        Button(
            onClick = {
                navController?.navigateUp()
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Go Back Home")
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}