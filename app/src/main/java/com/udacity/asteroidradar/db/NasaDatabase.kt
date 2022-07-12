package com.udacity.asteroidradar.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.udacity.asteroidradar.model.Asteroid

@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NasaDatabase : RoomDatabase() {
    abstract fun nasaDao(): NasaDao
}