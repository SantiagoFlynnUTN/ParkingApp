package org.flynndevs.com.feature.home.state

import org.flynndevs.com.mvi.ReduceAction
import org.flynndevs.com.mvi.ViewIntent
import org.flynndevs.com.mvi.ViewState

// State for the Home screen
data class HomeViewState(
    val welcomeMessage: String = "Welcome Home!",
    val isLoading: Boolean = false
) : ViewState

// Intents that can be dispatched from the UI
sealed class HomeViewIntent : ViewIntent {
    data class UpdateWelcomeMessage(val message: String) : HomeViewIntent()
}

// Actions for the reducer to handle
sealed class HomeReduceAction : ReduceAction {
    data class SetWelcomeMessage(val message: String) : HomeReduceAction()
    data class SetLoading(val isLoading: Boolean) : HomeReduceAction()
}