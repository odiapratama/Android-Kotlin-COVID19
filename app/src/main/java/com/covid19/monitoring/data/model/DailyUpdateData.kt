package com.covid19.monitoring.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.covid19.monitoring.utils.toNumberSeparator

@Entity(tableName = "daily_update_data")
data class DailyUpdateData(
    @PrimaryKey
    val objectid: Int? = 0,
    val dailyUpdateId: Int = 0,
    val reportDate: String?,
    val mainlandChina: Int?,
    val otherLocations: Int?,
    val totalConfirmed: Int?,
    val totalRecovered: Int?,
    val reportDateString: String?,
    val deltaConfirmed: Int?,
    val deltaRecovered: Int?
) {
    fun getTotalConfirmedWithSeparator() = totalConfirmed?.toNumberSeparator() ?: "0"

    fun getTotalRecoveredWithSeparator() = totalRecovered?.toNumberSeparator() ?: "0"

    fun getMainLandChinaWithSeparator() = mainlandChina?.toNumberSeparator() ?: "0"

    fun getOtherLocationsWithSeparator() = otherLocations?.toNumberSeparator() ?: "0"
}