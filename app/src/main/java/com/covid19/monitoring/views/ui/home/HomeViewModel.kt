package com.covid19.monitoring.views.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.covid19.monitoring.base.LiveViewModel
import com.covid19.monitoring.model.RegionData
import com.covid19.monitoring.services.Repository
import com.covid19.monitoring.services.Resource

class HomeViewModel(repository: Repository) : LiveViewModel() {

    private var fetchingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var listRegionData: LiveData<Resource<List<RegionData>>>

    init {
        this.listRegionData = this.fetchingLiveData.switchMap {
            launchOnViewModelScope {
                repository.getRegionData()
            }
        }
    }

    fun fetch() = fetchingLiveData.postValue(true)
}