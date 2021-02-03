package com.covid19.monitoring.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.withTransaction
import com.covid19.monitoring.data.model.DailyUpdateData
import com.covid19.monitoring.data.model.GlobalData
import com.covid19.monitoring.data.model.RegionData
import com.covid19.monitoring.data.source.database.AppDao
import com.covid19.monitoring.data.source.database.AppDatabase
import com.covid19.monitoring.data.source.database.AppPreferences
import com.covid19.monitoring.data.source.remote.Api
import com.covid19.monitoring.services.CoroutineContextProviders
import com.covid19.monitoring.services.NetworkBoundResource
import com.covid19.monitoring.services.Resource
import com.covid19.monitoring.utils.getCurrentDate

class RepositoryImpl(
    private val api: Api,
    private val appDao: AppDao,
    private val appPref: AppPreferences,
    private val appDatabase: AppDatabase,
    private val contextProviders: CoroutineContextProviders
) : Repository {

    override fun getGlobalData(): LiveData<Resource<GlobalData>> {
        return object : NetworkBoundResource<GlobalData, GlobalData>(contextProviders) {

            override suspend fun loadFromDB(): GlobalData? = appPref.getGlobalData()

            override fun setLatestFetch() = appPref.setGlobalDataLatestFetch(getCurrentDate())

            override fun getFetchDate(data: GlobalData?) = appPref.getGlobalDataLatestFetch() ?: ""

            override fun shouldFetchData(data: GlobalData?, fetchDate: String): Boolean {
                return fetchDate != getCurrentDate()
            }

            override suspend fun remoteCall() = api.getGlobalData()

            override suspend fun saveRemoteData(data: GlobalData) = appPref.setGlobalData(data)

            override suspend fun clearData() = appPref.clearGlobalData()

        }.data
    }

    override fun getDailyUpdateData(): LiveData<Resource<List<DailyUpdateData>>> {
        return object :
            NetworkBoundResource<List<DailyUpdateData>, List<DailyUpdateData>>(contextProviders) {

            override suspend fun loadFromDB(): List<DailyUpdateData> =
                appDao.getAllDailyUpdateData()

            override fun setLatestFetch() = appPref.setDailyUpdateLatestFetch(getCurrentDate())

            override fun getFetchDate(data: List<DailyUpdateData>?) =
                appPref.getDailyUpdateLatestFetch() ?: ""

            override fun shouldFetchData(data: List<DailyUpdateData>?, fetchDate: String): Boolean {
                return fetchDate != getCurrentDate()
            }

            override suspend fun remoteCall() = api.getDailyUpdates()

            override suspend fun saveRemoteData(data: List<DailyUpdateData>) =
                appDatabase.withTransaction {
                    appDao.insertAllDailyUpdateData(data)
                }

            override suspend fun clearData() = appDao.clearAllDailyUpdateData()

        }.data
    }

    override fun getRegionData(): LiveData<Resource<List<RegionData>>> {
        return object : NetworkBoundResource<List<RegionData>, List<RegionData>>(
            contextProviders
        ) {

            override suspend fun loadFromDB(): List<RegionData> = appDao.getAllRegionData()

            override fun setLatestFetch() = appPref.setRegionDataLatestFetch(getCurrentDate())

            override fun getFetchDate(data: List<RegionData>?) =
                appPref.getRegionDataLatestFetch() ?: ""

            override fun shouldFetchData(data: List<RegionData>?, fetchDate: String): Boolean {
                return fetchDate != getCurrentDate()
            }

            override suspend fun remoteCall() = api.getRegionData()

            override suspend fun saveRemoteData(data: List<RegionData>) =
                appDatabase.withTransaction {
                    appDao.insertAllRegionData(data)
                }

            override suspend fun clearData() = appDatabase.withTransaction {
                appDao.clearAllRegionData()
            }

        }.data
    }

    override suspend fun getRegionDataSource(): DataSource.Factory<Int, RegionData> =
        appDatabase.withTransaction {
            appDao.getAllRegionDataSource()
        }

    override suspend fun getDailyUpdateDataSource(): DataSource.Factory<Int, DailyUpdateData> =
        appDatabase.withTransaction {
            appDao.getAllDailyUpdateDataSource()
        }
}