package org.flynndevs.com.feature.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
actual fun MapComponent(
    latitude: Double?,
    longitude: Double?,
    onLocationUpdate: (Double, Double) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Location provider client
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Location permission state
    val hasLocationPermission = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // Loading state for map
    val isLoading = remember { mutableStateOf(true) }

    // Default location (San Francisco) if user location is not available
    val defaultPosition = LatLng(37.7749, -122.4194)

    // Current position from props or default
    val currentPosition = if (latitude != null && longitude != null) {
        LatLng(latitude, longitude)
    } else {
        defaultPosition
    }

    // Camera position state that controls map view
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentPosition, 15f)
    }

    // Map UI settings
    val mapUiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = true,
            myLocationButtonEnabled = hasLocationPermission.value,
            compassEnabled = true
        )
    }

    // Map properties
    val mapProperties = remember(hasLocationPermission.value) {
        MapProperties(
            isMyLocationEnabled = hasLocationPermission.value,
            mapType = MapType.NORMAL
        )
    }

    // Handle location permission request
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasLocationPermission.value = isGranted
        if (isGranted) {
            // Start location updates when permission is granted
            requestLocationUpdates(fusedLocationClient, onLocationUpdate)
        }
    }

    // Request location permission if not already granted
    LaunchedEffect(Unit) {
        if (!hasLocationPermission.value) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            // If permission already granted, start location updates
            requestLocationUpdates(fusedLocationClient, onLocationUpdate)
        }
    }

    // Update camera position when location changes
    LaunchedEffect(currentPosition) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(currentPosition, 15f)
        isLoading.value = false
    }

    // Location callback for continuous updates
    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { location ->
                    onLocationUpdate(location.latitude, location.longitude)
                }
            }
        }
    }

    // Start/stop location updates when component is created/destroyed
    DisposableEffect(Unit) {
        if (hasLocationPermission.value) {
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(5000)
                .build()

            try {
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
            } catch (securityException: SecurityException) {
                // Handle security exception if permission was revoked
                securityException.printStackTrace()
            }
        }

        onDispose {
            try {
                fusedLocationClient.removeLocationUpdates(locationCallback)
            } catch (securityException: SecurityException) {
                securityException.printStackTrace()
            }
        }
    }

    // Map UI
    Box(modifier = modifier) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings,
            onMapLoaded = { isLoading.value = false },
            onMapClick = { latLng ->
                coroutineScope.launch {
                    onLocationUpdate(latLng.latitude, latLng.longitude)
                }
            }
        ) {
            // Show marker at user's location
            if (latitude != null && longitude != null) {
                Marker(
                    state = MarkerState(position = LatLng(latitude, longitude)),
                    title = "Your Location"
                )
            }
        }

        // Show loading indicator
        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

// Helper function to request location updates
private fun requestLocationUpdates(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationUpdate: (Double, Double) -> Unit
) {
    try {
        // Check permission before accessing location
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    onLocationUpdate(it.latitude, it.longitude)
                }
            }
            .addOnFailureListener { exception ->
                // Handle failures, such as permission denial or location disabled
                exception.printStackTrace()
            }
    } catch (securityException: SecurityException) {
        // Handle the exception when permission is denied
        securityException.printStackTrace()
    }
}