package com.covid19.monitoring.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.covid19.monitoring.data.model.DailyUpdateData
import com.covid19.monitoring.data.model.GlobalData
import com.covid19.monitoring.data.model.RegionData
import com.covid19.monitoring.services.Resource

interface Repository {
    fun getGlobalData(): LiveData<Resource<GlobalData>>
    fun getDailyUpdateData(): LiveData<Resource<List<DailyUpdateData>>>
    fun getRegionData(): LiveData<Resource<List<RegionData>>>
    suspend fun getRegionDataSource(): DataSource.Factory<Int, RegionData>
    suspend fun getDailyUpdateDataSource(): DataSource.Factory<Int, DailyUpdateData>
}