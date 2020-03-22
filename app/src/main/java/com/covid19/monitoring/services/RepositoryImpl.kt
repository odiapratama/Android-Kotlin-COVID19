package com.covid19.monitoring.services

import androidx.lifecycle.MutableLiveData
import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.model.RegionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl(private val api: Api) : Repository {

    override suspend fun getGlobalData() = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<Resource<GlobalData>>()
        api.getGlobalData().enqueue(object : Callback<GlobalData> {
            override fun onFailure(call: Call<GlobalData>, t: Throwable) {
                liveData.postValue(Resource.error(t.message ?: ""))
            }

            override fun onResponse(call: Call<GlobalData>, response: Response<GlobalData>) {
                liveData.postValue(Resource.success(response.body()))
            }
        })
        return@withContext liveData
    }

    override suspend fun getDailyUpdateData() = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<Resource<List<DailyUpdateData>>>()
        api.getDailyUpdates().enqueue(object : Callback<List<DailyUpdateData>> {
            override fun onFailure(call: Call<List<DailyUpdateData>>, t: Throwable) {
                liveData.postValue(Resource.error(t.message ?: ""))
            }

            override fun onResponse(
                call: Call<List<DailyUpdateData>>,
                response: Response<List<DailyUpdateData>>
            ) {
                liveData.postValue(Resource.success(response.body()))
            }
        })
        return@withContext liveData
    }

    override suspend fun getRegionData() = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<Resource<List<RegionData>>>()
        api.getRegionData().enqueue(object : Callback<List<RegionData>> {
            override fun onFailure(call: Call<List<RegionData>>, t: Throwable) {
                liveData.postValue(Resource.error(t.message ?: ""))
            }

            override fun onResponse(
                call: Call<List<RegionData>>,
                response: Response<List<RegionData>>
            ) {
                liveData.postValue(Resource.success(response.body()))
            }
        })
        return@withContext liveData
    }
}