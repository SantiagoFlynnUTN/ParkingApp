package org.flynndevs.com.mvi

/**
 * A [Middleware] is any class that deals with side effects of actions. This can be logging,
 * triggering network calls, and other examples.
 */
fun interface Middleware<S : ViewState, I : ViewIntent, A : ReduceAction> {
    /**
     * This will process the given [intent] and [currentState] and determine if we need to perform
     * any side effects, or trigger a new action.
     *
     * @param[dispatch] This is a lambda that will emit a new [ReduceAction].
     */
    suspend fun process(intent: I, currentState: () -> S, dispatch: suspend (A) -> Unit)
}
