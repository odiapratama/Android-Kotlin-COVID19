package com.covid19.monitoring.services

import androidx.lifecycle.LiveData
import com.covid19.monitoring.di.CoroutineContextProviders
import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.model.RegionData
import com.covid19.monitoring.utils.getCurrentDate

class RepositoryImpl(
    private val api: Api,
    private val appPref: AppPreferences,
    private val contextProviders: CoroutineContextProviders
) : Repository {

    override suspend fun getGlobalData(): LiveData<Resource<GlobalData>> {
        return object : NetworkBoundResource<GlobalData, GlobalData>(contextProviders) {

            override fun loadFromDB(): GlobalData? = appPref.getGlobalData()

            override fun setLatestFetch() = Unit

            override fun getFetchDate(data: GlobalData?) = ""

            override fun shouldFetchData(data: GlobalData?, fetchDate: String) = true

            override suspend fun remoteCall() = api.getGlobalData()

            override fun saveRemoteData(data: GlobalData) = appPref.setGlobalData(data)

            override fun clearData() = appPref.clearGlobalData()

        }.data
    }

    override suspend fun getDailyUpdateData(): LiveData<Resource<List<DailyUpdateData>>> {
        return object :
            NetworkBoundResource<List<DailyUpdateData>, List<DailyUpdateData>>(contextProviders) {

            override fun loadFromDB(): List<DailyUpdateData> = appPref.getDailyList() ?: emptyList()

            override fun setLatestFetch() = appPref.setLatestFetch(getCurrentDate())

            override fun getFetchDate(data: List<DailyUpdateData>?) = appPref.getLatestFetch() ?: ""

            override fun shouldFetchData(data: List<DailyUpdateData>?, fetchDate: String): Boolean {
                return fetchDate != getCurrentDate()
            }

            override suspend fun remoteCall() = api.getDailyUpdates()

            override fun saveRemoteData(data: List<DailyUpdateData>) = appPref.setDailyList(data)

            override fun clearData() = appPref.clearDailyData()

        }.data
    }

    override suspend fun getRegionData(): LiveData<Resource<List<RegionData>>> {
        return object : NetworkBoundResource<List<RegionData>, List<RegionData>>(contextProviders) {

            override fun loadFromDB(): List<RegionData>? = appPref.getRegionList()

            override fun setLatestFetch() = Unit

            override fun getFetchDate(data: List<RegionData>?) = ""

            override fun shouldFetchData(data: List<RegionData>?, fetchDate: String) = true

            override suspend fun remoteCall() = api.getRegionData()

            override fun saveRemoteData(data: List<RegionData>) = appPref.setRegionList(data)

            override fun clearData() = appPref.clearRegionList()

        }.data
    }
}