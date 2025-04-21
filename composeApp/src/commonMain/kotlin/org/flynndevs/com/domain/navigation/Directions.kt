package org.flynndevs.com.domain.navigation

import kotlinx.serialization.Serializable

interface Directions {
    @Serializable
    data object Home : Directions

    @Serializable
    data object Map : Directions
}