package com.covid19.monitoring.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.covid19.monitoring.utils.formatTime
import com.covid19.monitoring.utils.toNumberSeparator
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.NumberFormat

@Entity(tableName = "region_data")
data class RegionData(

    @PrimaryKey(autoGenerate = true)
    var regionDataId: Int = 0,

    var provinceState: String? = "",

    var countryRegion: String? = "",

    var lastUpdate: Long? = 0L,

    var lat: Double? = 0.0,

    @SerializedName("long")
    var lng: Double? = 0.0,

    var confirmed: Int? = 0,

    var recovered: Int? = 0,

    var deaths: Int? = 0,

    var active: Int? = 0,

    var iso2: String? = "",

    var iso3: String? = ""

) : Serializable {

    fun totalCases() = ((confirmed ?: 0) + (recovered ?: 0) + (deaths ?: 0)).toNumberSeparator()

    fun recoveryRate(): String = NumberFormat.getPercentInstance().format((recovered?.toDouble()?.div(confirmed ?: 0)))

    fun fatalityRate(): String = NumberFormat.getPercentInstance().format((deaths?.toDouble()?.div(confirmed ?: 0)))

    fun totalConfirmedWithSeparator() = confirmed?.toNumberSeparator() ?: "0"

    fun totalRecoveredWithSeparator() = recovered?.toNumberSeparator() ?: "0"

    fun totalDeathsWithSeparator() = deaths?.toNumberSeparator() ?: "0"

    fun location() = if (provinceState != null) "$provinceState, $countryRegion" else countryRegion

    fun lastUpdateFormat() = lastUpdate?.formatTime()
}