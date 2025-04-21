package org.flynndevs.com.di

import org.flynndevs.com.feature.home.HomeViewModel
import org.flynndevs.com.feature.map.MapViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::MapViewModel)
}