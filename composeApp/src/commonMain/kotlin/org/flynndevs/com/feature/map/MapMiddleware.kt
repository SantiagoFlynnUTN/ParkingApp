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
        }
    }
}