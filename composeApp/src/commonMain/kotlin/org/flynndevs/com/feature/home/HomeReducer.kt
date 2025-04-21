package org.flynndevs.com.feature.home

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