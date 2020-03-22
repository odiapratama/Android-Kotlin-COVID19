package com.covid19.monitoring.views.ui.statistics

import androidx.lifecycle.*
import com.covid19.monitoring.base.LiveViewModel
import com.covid19.monitoring.model.RegionData
import com.covid19.monitoring.services.Repository
import com.covid19.monitoring.services.Resource

class StatisticsViewModel(private val repository: Repository) : LiveViewModel() {

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
