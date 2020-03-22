package com.covid19.monitoring.services

import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.model.RegionData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

const val API_PATH = "api/"

interface Api {
    @GET(" ")
    fun getGlobalData(): Call<GlobalData>

    @GET("daily")
    fun getDailyUpdates(): Call<List<DailyUpdateData>>

    @GET("confirmed")
    fun getRegionData(): Call<List<RegionData>>
}