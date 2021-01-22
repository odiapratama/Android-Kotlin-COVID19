package com.covid19.monitoring.services

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.covid19.monitoring.model.DailyUpdateData
import com.covid19.monitoring.model.GlobalData
import com.covid19.monitoring.model.RegionData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AppPreferences(context: Context?) {

    private val privateMode = 0
    private val prefName = "AppPref"
    private val latestFetch = "LatestFetch"
    private val globalData = "GlobalData"
    private val trendingDailyUpdate = "DailyList"
    private val regionList = "RegionList"
    private val pref: SharedPreferences? = context?.getSharedPreferences(prefName, privateMode)

    fun setLatestFetch(dateString: String?) {
        dateString?.let {
            pref?.edit {
                putString(latestFetch, it)
            }
        }
    }

    fun getLatestFetch() = pref?.getString(latestFetch, null)

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

    fun setDailyList(data: List<DailyUpdateData>?) {
        data?.let {
            pref?.edit {
                putString(trendingDailyUpdate, Gson().toJson(it))
            }
        }
    }

    fun getDailyList(): List<DailyUpdateData>? {
        val listType: Type = object : TypeToken<List<DailyUpdateData>>() {}.type
        return Gson().fromJson(pref?.getString(trendingDailyUpdate, null), listType)
    }

    fun clearDailyData() {
        pref?.edit {
            putString(trendingDailyUpdate, null)
        }
    }

    fun setRegionList(data: List<RegionData>?) {
        data?.let {
            pref?.edit {
                putString(regionList, Gson().toJson(it))
            }
        }
    }

    fun getRegionList(): List<RegionData>? {
        val listType: Type = object : TypeToken<List<RegionData>>() {}.type
        return Gson().fromJson(pref?.getString(regionList, null), listType)
    }

    fun clearRegionList() {
        pref?.edit {
            putString(regionList, null)
        }
    }
}