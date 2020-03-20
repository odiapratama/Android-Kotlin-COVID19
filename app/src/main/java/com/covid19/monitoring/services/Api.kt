package com.covid19.monitoring.services

import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import retrofit2.Response
import retrofit2.http.GET

const val API_PATH = "api/"

interface Api {
    @GET(" ")
    suspend fun getGlobalData(): Response<GlobalData>

    @GET("daily")
    suspend fun getDailyUpdates(): Response<List<DailyUpdateData>>
}