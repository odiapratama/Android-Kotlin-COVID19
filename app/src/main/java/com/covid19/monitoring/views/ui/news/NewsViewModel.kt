package com.covid19.monitoring.views.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.toLiveData
import com.covid19.monitoring.base.LiveViewModel
import com.covid19.monitoring.data.repository.Repository

class NewsViewModel(private val repository: Repository) : LiveViewModel() {

    val fetchingLiveData = MutableLiveData<Boolean>()

    val dailyUpdateData = fetchingLiveData.switchMap {
        repository.getDailyUpdateData()
    }

    val globalData = fetchingLiveData.switchMap {
        repository.getGlobalData()
    }

    val dailyUpdatesDataSource = fetchingLiveData.switchMap {
        launchOnViewModelScope {
            repository.getDailyUpdateDataSource().toLiveData(30)
        }
    }

    fun fetchData() {
        fetchingLiveData.value = true
    }
}
