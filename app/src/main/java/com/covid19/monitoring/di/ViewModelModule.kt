package com.covid19.monitoring.di

import com.covid19.monitoring.views.ui.detail.DetailViewModel
import com.covid19.monitoring.views.ui.home.HomeViewModel
import com.covid19.monitoring.views.ui.news.NewsViewModel
import com.covid19.monitoring.views.ui.statistics.StatisticsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { NewsViewModel(get()) }
    viewModel { StatisticsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}