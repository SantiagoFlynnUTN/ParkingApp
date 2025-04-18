package org.flynndevs.com.mvi

import kotlinx.coroutines.flow.StateFlow

/**
 * A [ViewState] is used to manage [ViewState] and dispatch [ReduceAction]s that can update the state.
 */
interface Store<S : ViewState, I : ViewIntent, A : ReduceAction> {
    /**
     * Represents the current state of the UI
     */
    val state: StateFlow<S>

    /**
     * Called when an event is triggered, like a button click, etc.
     */
    fun onIntent(intent: I)
}
