package com.covid19.monitoring.data.source.database

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.covid19.monitoring.data.model.DailyUpdateData
import com.covid19.monitoring.data.model.GlobalData
import com.covid19.monitoring.data.model.RegionData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AppPreferences(context: Context?) {

    private val privateMode = 0
    private val prefName = "AppPref"
    private val globalDataLatestFetch = "GlobalDataLatestFetch"
    private val dailyUpdateLatestFetch = "DailyUpdateLatestFetch"
    private val regionDataLatestFetch = "RegionDataLatestFetch"
    private val globalData = "GlobalData"
    private val pref: SharedPreferences? = context?.getSharedPreferences(prefName, privateMode)

    fun setGlobalData(data: GlobalData?) {
        data?.let {
            pref?.edit {
                putString(globalData, Gson().toJson(data))
            }
        }
    }

    fun getGlobalData(): GlobalData? {
        return Gson().fromJson(pref?.getString(globalData, null), GlobalData::class.java)
    }

    fun clearGlobalData() {
        pref?.edit {
            putString(globalData, null)
        }
    }

    fun setGlobalDataLatestFetch(dateString: String?) {
        dateString?.let {
            pref?.edit {
                putString(globalDataLatestFetch, it)
            }
        }
    }

    fun getGlobalDataLatestFetch() = pref?.getString(globalDataLatestFetch, null)

    fun setDailyUpdateLatestFetch(dateString: String?) {
        dateString?.let {
            pref?.edit {
                putString(dailyUpdateLatestFetch, it)
            }
        }
    }

    fun getDailyUpdateLatestFetch() = pref?.getString(dailyUpdateLatestFetch, null)

    fun setRegionDataLatestFetch(dateString: String?) {
        dateString?.let {
            pref?.edit {
                putString(regionDataLatestFetch, it)
            }
        }
    }

    fun getRegionDataLatestFetch() = pref?.getString(regionDataLatestFetch, null)
}