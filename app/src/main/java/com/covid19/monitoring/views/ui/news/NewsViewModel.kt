package com.covid19.monitoring.views.ui.news

import androidx.lifecycle.*
import com.covid19.monitoring.base.LiveViewModel
import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.services.Api
import com.covid19.monitoring.services.Repository
import com.covid19.monitoring.services.Resource
import kotlinx.coroutines.Dispatchers

class NewsViewModel(private val repository: Repository) : LiveViewModel() {

    private val fetchingLiveData = MutableLiveData<Boolean>()
    val globalData: LiveData<Resource<GlobalData>>
    val dailyUpdatesData: LiveData<Resource<List<DailyUpdateData>>>

    init {
        globalData = fetchingLiveData.switchMap {
            launchOnViewModelScope {
                repository.getGlobalData()
            }
        }
        dailyUpdatesData = fetchingLiveData.switchMap {
            launchOnViewModelScope {
                repository.getDailyUpdateData()
            }
        }
    }

    fun fetchData() = fetchingLiveData.postValue(true)
}
