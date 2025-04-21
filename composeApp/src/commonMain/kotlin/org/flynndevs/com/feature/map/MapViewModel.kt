package org.flynndevs.com.feature.map

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import org.flynndevs.com.mvi.MviStore
import org.flynndevs.com.mvi.Store

class MapViewModel : ViewModel(),
    Store<MapViewState,
            MapViewIntent,
            MapReduceAction> {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val store = MviStore(
        initialState = MapViewState(),
        scope = scope,
        reducer = MapReducer(),
        middlewares = listOf(
            MapMiddleware()
        )
    )

    override val state: StateFlow<MapViewState> = store.state

    override fun onIntent(intent: MapViewIntent) {
        store.onIntent(intent)
    }
}