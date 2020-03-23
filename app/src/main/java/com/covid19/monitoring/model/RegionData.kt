package com.covid19.monitoring.model

import com.covid19.monitoring.utils.formatTime
import com.covid19.monitoring.utils.toNumberSeparator
import java.io.Serializable
import java.text.NumberFormat

data class RegionData(
    val provinceState: String?,
    val countryRegion: String?,
    val lastUpdate: Long?,
    val lat: Double?,
    val long: Double?,
    val confirmed: Int?,
    val recovered: Int?,
    val deaths: Int?,
    val active: Int?,
    val iso2: String?,
    val iso3: String?
) : Serializable {
    val totalCases get() =  ((confirmed ?: 0) + (recovered ?: 0) + (deaths ?: 0)).toNumberSeparator()
    val recoveryRate: String get() = NumberFormat.getPercentInstance().format((recovered?.toDouble()?.div(confirmed ?: 0)))
    val fatalityRate: String get() = NumberFormat.getPercentInstance().format((deaths?.toDouble()?.div(confirmed ?: 0)))
    val totalConfirmedWithSeparator get() = confirmed?.toNumberSeparator() ?: "0"
    val totalRecoveredWithSeparator get() = recovered?.toNumberSeparator() ?: "0"
    val totalDeathsWithSeparator get() = deaths?.toNumberSeparator() ?: "0"
    val location get() = if (provinceState != null) "$provinceState, $countryRegion" else countryRegion
    val lastUpdateFormat get() = lastUpdate?.formatTime()
}