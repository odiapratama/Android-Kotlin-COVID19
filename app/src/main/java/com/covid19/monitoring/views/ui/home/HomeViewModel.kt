package com.covid19.monitoring.views.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.paging.toLiveData
import com.covid19.monitoring.base.LiveViewModel
import com.covid19.monitoring.data.repository.Repository

class HomeViewModel(private val repository: Repository) : LiveViewModel() {

    private var fetchingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val listRegionData = fetchingLiveData.switchMap {
        repository.getRegionData()
    }

    val regionDataSource = fetchingLiveData.switchMap {
        launchOnViewModelScope {
            repository.getRegionDataSource().toLiveData(30)
        }
    }

    fun fetch() {
        fetchingLiveData.value = true
    }
}