package org.flynndevs.com

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform