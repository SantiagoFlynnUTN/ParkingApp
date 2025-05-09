package org.flynndevs.com.feature.home

import org.flynndevs.com.mvi.Middleware

class HomeMiddleware : Middleware<HomeViewState, HomeViewIntent, HomeReduceAction> {
    override suspend fun process(
        intent: HomeViewIntent,
        currentState: () -> HomeViewState,
        dispatch: suspend (HomeReduceAction) -> Unit
    ) {
        when (intent) {
            is HomeViewIntent.UpdateWelcomeMessage -> {
                dispatch(HomeReduceAction.SetLoading(true))

                // Simulate some processing or network call
                dispatch(HomeReduceAction.SetWelcomeMessage(intent.message))

                dispatch(HomeReduceAction.SetLoading(false))
            }
        }
    }
}