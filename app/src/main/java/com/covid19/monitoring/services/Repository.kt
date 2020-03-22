package com.covid19.monitoring.services

import androidx.lifecycle.MutableLiveData
import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.model.RegionData

interface Repository {
    suspend fun getGlobalData(): MutableLiveData<Resource<GlobalData>>
    suspend fun getDailyUpdateData(): MutableLiveData<Resource<List<DailyUpdateData>>>
    suspend fun getRegionData(): MutableLiveData<Resource<List<RegionData>>>
}