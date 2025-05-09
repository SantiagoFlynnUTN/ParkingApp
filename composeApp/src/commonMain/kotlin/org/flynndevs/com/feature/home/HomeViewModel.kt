package org.flynndevs.com.feature.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import org.flynndevs.com.mvi.MviStore
import org.flynndevs.com.mvi.Store

class HomeViewModel(

) : ViewModel(),
    Store<HomeViewState,
        HomeViewIntent,
        HomeReduceAction> {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val store = MviStore(
        initialState = HomeViewState(),
        scope = scope,
        reducer = HomeReducer(),
        middlewares = listOf(
            HomeMiddleware()
        )
    )

    override val state: StateFlow<HomeViewState> = store.state

    override fun onIntent(intent: HomeViewIntent) {
        store.onIntent(intent)
    }
}