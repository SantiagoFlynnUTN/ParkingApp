package org.flynndevs.com.di

import org.flynndevs.com.feature.home.HomeViewModel
import org.flynndevs.com.feature.home.createHomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin() {
    // Stop Koin if already started to prevent duplicate initialization
    try {
        stopKoin()
    } catch (e: IllegalStateException) {
        // Koin not started yet
    }

    startKoin {
        modules(appModule())
    }
}

fun appModule(): List<Module> = listOf(
    viewModelModule
)

val viewModelModule = module {
    factory { createHomeViewModel() }
}