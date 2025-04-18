package org.flynndevs.com

import androidx.compose.ui.window.ComposeUIViewController
import org.flynndevs.com.di.initKoinIos

// Initialize Koin for iOS
private val koinApp = initKoinIos()

fun MainViewController() = ComposeUIViewController { App() }