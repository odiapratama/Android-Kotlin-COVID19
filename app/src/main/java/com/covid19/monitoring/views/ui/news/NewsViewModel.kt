package com.covid19.monitoring.views.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.covid19.monitoring.services.Api
import com.covid19.monitoring.services.Resource
import kotlinx.coroutines.Dispatchers

class NewsViewModel(private val api: Api) : ViewModel() {

    fun getGlobalData() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = api.getGlobalData()
        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message()))
        }
    }

    fun getDailyUpdatesData() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = api.getDailyUpdates()
        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message()))
        }
    }
}
