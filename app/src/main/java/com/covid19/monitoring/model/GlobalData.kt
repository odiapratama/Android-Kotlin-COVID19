package com.covid19.monitoring.model

data class GlobalData(
    val confirmed: GlobalValue?,
    val recovered: GlobalValue?,
    val deaths: GlobalValue?,
    val dailySummary: String?,
    val dailyTimeSeries: GlobalDetail?,
    val image: String?,
    val source: String?,
    val countries: String?,
    val countryDetail: GlobalDetail?,
    val lastUpdate: String?
)

data class GlobalValue(
    val value: Int?,
    val detail: String?
)

data class GlobalDetail(
    val pattern: String?,
    val example: String?
)