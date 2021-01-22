package com.covid19.monitoring.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.model.RegionData

interface Repository {
    suspend fun getGlobalData(): LiveData<Resource<GlobalData>>
    suspend fun getDailyUpdateData(): LiveData<Resource<List<DailyUpdateData>>>
    suspend fun getRegionData(): LiveData<Resource<List<RegionData>>>
}