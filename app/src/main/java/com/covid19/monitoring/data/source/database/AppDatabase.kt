package com.covid19.monitoring.data.source.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.covid19.monitoring.data.model.DailyUpdateData
import com.covid19.monitoring.data.model.RegionData

@Database(
    entities = [
        RegionData::class,
        DailyUpdateData::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}

fun provideDatabase(application: Application) = Room.databaseBuilder(
    application,
    AppDatabase::class.java,
    "TEMP_DB1"
).fallbackToDestructiveMigration().build()