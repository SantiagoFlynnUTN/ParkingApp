package org.flynndevs.com.mvi

/**
 * A [Reducer] is responsible for taking a [ViewState], and an [ReduceAction],
 * and emitting a new state via the [reduce] method.
 */
fun interface Reducer<S : ViewState, A : ReduceAction> {
    /**
     * Given a [currentState] and some [action] that the user took, produce a new [ViewState].
     *
     * This will give us clear and predictable state management, that ensures each state is associated
     * with some specific user intent or action.
     */
    fun reduce(currentState: S, action: A): S
}
