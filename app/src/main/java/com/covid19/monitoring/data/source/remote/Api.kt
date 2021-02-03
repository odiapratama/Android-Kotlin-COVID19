package com.covid19.monitoring.data.source.remote

import com.covid19.monitoring.data.model.DailyUpdateData
import com.covid19.monitoring.data.model.GlobalData
import com.covid19.monitoring.data.model.RegionData
import retrofit2.http.GET

const val API_PATH = "api/"

interface Api {
    @GET(" ")
    suspend fun getGlobalData(): GlobalData

    @GET("daily")
    suspend fun getDailyUpdates(): List<DailyUpdateData>

    @GET("confirmed")
    suspend fun getRegionData(): List<RegionData>
}