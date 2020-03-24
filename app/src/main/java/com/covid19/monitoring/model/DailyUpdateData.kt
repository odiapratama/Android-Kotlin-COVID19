package com.covid19.monitoring.model

import com.covid19.monitoring.utils.toNumberSeparator

data class DailyUpdateData(
    val reportDate: String?,
    val mainlandChina: Int?,
    val otherLocations: Int?,
    val totalConfirmed: Int?,
    val totalRecovered: Int?,
    val reportDateString: String?,
    val deltaConfirmed: Int?,
    val deltaRecovered: Int?,
    val objectid: Int?
) {
    fun getTotalConfirmedWithSeparator() = totalConfirmed?.toNumberSeparator() ?: "0"
    fun getTotalRecoveredWithSeparator() = totalRecovered?.toNumberSeparator() ?: "0"
    fun getMainLandChinaWithSeparator() = mainlandChina?.toNumberSeparator() ?: "0"
    fun getOtherLocationsWithSeparator() = otherLocations?.toNumberSeparator() ?: "0"
}