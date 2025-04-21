package org.flynndevs.com.feature.map

import org.flynndevs.com.mvi.ReduceAction
import org.flynndevs.com.mvi.ViewIntent
import org.flynndevs.com.mvi.ViewState

// State for the Map screen
data class MapViewState(
    val title: String = "Map View",
    val isLoading: Boolean = false
) : ViewState

// Intents that can be dispatched from the UI
sealed class MapViewIntent : ViewIntent {
    data class UpdateTitle(val title: String) : MapViewIntent()
}

// Actions for the reducer to handle
sealed class MapReduceAction : ReduceAction {
    data class SetTitle(val title: String) : MapReduceAction()
    data class SetLoading(val isLoading: Boolean) : MapReduceAction()
}