package com.covid19.monitoring

import android.app.Application
import android.content.Context
import com.covid19.monitoring.di.serviceModule
import com.covid19.monitoring.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        @Volatile
        private lateinit var INSTANCE: App

        fun appContext(): Context {
            return INSTANCE.applicationContext
        }
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(serviceModule)
            modules(viewModelModule)
            properties(
                mapOf("global_api" to "https://covid19.mathdro.id/")
            )
        }
    }
}