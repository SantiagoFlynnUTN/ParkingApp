package org.flynndevs.com.feature.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import org.flynndevs.com.feature.home.middleware.HomeMiddleware
import org.flynndevs.com.feature.home.reducer.HomeReducer
import org.flynndevs.com.feature.home.state.HomeReduceAction
import org.flynndevs.com.feature.home.state.HomeViewIntent
import org.flynndevs.com.feature.home.state.HomeViewState
import org.flynndevs.com.mvi.MviStore
import org.flynndevs.com.mvi.Store

class HomeViewModel(
    private val store: Store<HomeViewState, HomeViewIntent, HomeReduceAction>
) {
    val state: StateFlow<HomeViewState> = store.state

    fun processIntent(intent: HomeViewIntent) {
        store.onIntent(intent)
    }
}

// Factory function to create the HomeViewModel
fun createHomeViewModel(): HomeViewModel {
    val initialState = HomeViewState()
    val reducer = HomeReducer()
    val middleware = HomeMiddleware()

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val store = MviStore(
        initialState = initialState,
        scope = scope,
        reducer = reducer,
        middlewares = listOf(middleware)
    )

    return HomeViewModel(store)
}