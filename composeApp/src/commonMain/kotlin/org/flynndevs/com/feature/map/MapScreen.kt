package org.flynndevs.com.feature.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.displayMedium
        )

        Text(
            text = "Map screen placeholder",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = {
                viewModel.onIntent(MapViewIntent.UpdateTitle("Updated Map View"))
            },
            modifier = Modifier.padding(top = 16.dp)
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