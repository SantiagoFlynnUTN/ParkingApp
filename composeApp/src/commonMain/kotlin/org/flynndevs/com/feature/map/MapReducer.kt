package org.flynndevs.com.feature.map

import org.flynndevs.com.mvi.Reducer

class MapReducer : Reducer<MapViewState, MapReduceAction> {
    override fun reduce(currentState: MapViewState, action: MapReduceAction): MapViewState {
        return when (action) {
            is MapReduceAction.SetTitle -> currentState.copy(
                title = action.title
            )

            is MapReduceAction.SetLoading -> currentState.copy(
                isLoading = action.isLoading
            )
        }
    }
}