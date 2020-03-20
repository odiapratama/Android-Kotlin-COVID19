package com.covid19.monitoring

import android.app.Application
import com.covid19.monitoring.di.serviceModule
import com.covid19.monitoring.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(serviceModule)
            modules(viewModelModule)
            properties(
                mapOf("global_api" to "https://covid19.mathdro.id/")
            )
        }
    }
}