package org.flynndevs.com

import android.app.Application
import org.flynndevs.com.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin with Android context
        startKoin {
            androidContext(this@MyApplication)
            modules(org.flynndevs.com.di.appModule())
        }
    }
}