package com.covid19.monitoring.services

import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.model.RegionData
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