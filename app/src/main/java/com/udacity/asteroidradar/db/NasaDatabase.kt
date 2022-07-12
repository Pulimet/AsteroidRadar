package com.udacity.asteroidradar.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay

@Database(entities = [Asteroid::class, PictureOfDay::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NasaDatabase : RoomDatabase() {
    abstract fun nasaDao(): NasaDao
    abstract fun pictureDao(): PictureDao
}