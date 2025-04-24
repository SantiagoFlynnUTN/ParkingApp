package org.flynndevs.com.feature.map

import org.flynndevs.com.mvi.Middleware

class MapMiddleware : Middleware<MapViewState, MapViewIntent, MapReduceAction> {
    override suspend fun process(
        intent: MapViewIntent,
        currentState: () -> MapViewState,
        dispatch: suspend (MapReduceAction) -> Unit
    ) {
        when (intent) {
            is MapViewIntent.UpdateTitle -> {
                dispatch(MapReduceAction.SetLoading(true))

                // Simulate some processing or network call
                dispatch(MapReduceAction.SetTitle(intent.title))

                dispatch(MapReduceAction.SetLoading(false))
            }

            is MapViewIntent.RequestLocationPermission -> {
                // In the common code we just update the state
                // Platform specific implementation will handle actual permission request
                dispatch(MapReduceAction.SetLoading(true))
                // Permission handling will be implemented in platform-specific code
                dispatch(MapReduceAction.SetLoading(false))
            }

            is MapViewIntent.GetUserLocation -> {
                // Location retrieval will be implemented in platform-specific code
                dispatch(MapReduceAction.SetLoading(true))
                // This is a placeholder - the actual implementation will be in platform-specific code
                dispatch(MapReduceAction.SetLoading(false))
            }

            is MapViewIntent.UpdateUserLocation -> {
                dispatch(MapReduceAction.SetUserLocation(intent.latitude, intent.longitude))
            }
        }
    }
}