package org.flynndevs.com

import androidx.compose.ui.window.ComposeUIViewController
import org.flynndevs.com.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }