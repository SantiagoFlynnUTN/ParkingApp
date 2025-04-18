package org.flynndevs.com.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

// Function to initialize Koin for iOS
fun initKoinIos(): KoinApplication {
    return startKoin {
        modules(appModule())
    }
}