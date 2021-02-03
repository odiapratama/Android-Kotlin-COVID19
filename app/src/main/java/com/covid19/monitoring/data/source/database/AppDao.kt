package com.covid19.monitoring.data.source.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.covid19.monitoring.data.model.DailyUpdateData
import com.covid19.monitoring.data.model.RegionData

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRegionData(data: List<RegionData>)

    @Query("DELETE FROM region_data")
    suspend fun clearAllRegionData()

    @Query("SELECT * FROM region_data")
    fun getAllRegionDataSource(): DataSource.Factory<Int, RegionData>

    @Query("SELECT * FROM region_data")
    suspend fun getAllRegionData(): List<RegionData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDailyUpdateData(data: List<DailyUpdateData>)

    @Query("DELETE FROM daily_update_data")
    suspend fun clearAllDailyUpdateData()

    @Query("SELECT * FROM daily_update_data")
    fun getAllDailyUpdateDataSource(): DataSource.Factory<Int, DailyUpdateData>

    @Query("SELECT * FROM daily_update_data")
    suspend fun getAllDailyUpdateData(): List<DailyUpdateData>
}