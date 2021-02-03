package com.covid19.monitoring.di

import com.covid19.monitoring.data.repository.Repository
import com.covid19.monitoring.data.repository.RepositoryImpl
import com.covid19.monitoring.data.source.database.AppDatabase
import com.covid19.monitoring.data.source.database.AppPreferences
import com.covid19.monitoring.data.source.database.provideDatabase
import com.covid19.monitoring.data.source.remote.API_PATH
import com.covid19.monitoring.data.source.remote.Api
import com.covid19.monitoring.services.CoroutineContextProviders
import com.covid19.monitoring.services.createApi
import com.covid19.monitoring.services.createOkHttpClient
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val serviceModule = module {
    single { createOkHttpClient() }
    single {
        createApi<Api>(
            API_PATH,
            get(),
            getProperty("global_api")
        )
    }
    single { AppPreferences(get()) }
    single<Repository> { RepositoryImpl(get(), get(), get(), get(), get()) }
    single {
        CoroutineContextProviders(
            Dispatchers.IO
        )
    }
    single { provideDatabase(androidApplication()) }
    single { get<AppDatabase>().appDao() }
}
