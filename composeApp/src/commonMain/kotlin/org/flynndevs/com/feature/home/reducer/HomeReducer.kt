package org.flynndevs.com.feature.home.reducer

import org.flynndevs.com.feature.home.state.HomeReduceAction
import org.flynndevs.com.feature.home.state.HomeViewState
import org.flynndevs.com.mvi.Reducer

class HomeReducer : Reducer<HomeViewState, HomeReduceAction> {
    override fun reduce(currentState: HomeViewState, action: HomeReduceAction): HomeViewState {
        return when (action) {
            is HomeReduceAction.SetWelcomeMessage -> currentState.copy(
                welcomeMessage = action.message
            )

            is HomeReduceAction.SetLoading -> currentState.copy(
                isLoading = action.isLoading
            )
        }
    }
}