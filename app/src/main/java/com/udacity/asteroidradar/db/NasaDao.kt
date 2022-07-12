package com.udacity.asteroidradar.db

import androidx.room.*
import com.udacity.asteroidradar.model.Asteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface NasaDao {
    // TODO Fetch only Asteroids from today
    // TODO Sort Asteroid by date
    // TODO Be able to cache the data of the asteroid by using a worker, so it downloads and saves today's asteroids in
    //  background once a day when the device is charging and wifi is enabled.
    // TODO Add content description to the views: Details images and dialog button. Check if it works correctly with talk back.
    // TODO Make sure the entire app works without an internet connection. (Picture of the day for example)
    // TODO Modify the app to support multiple languages, device sizes, and orientations.
    // TODO Make the app delete asteroids from the previous day once a day using the same workManager that downloads the asteroids.
    // TODO Match the styles for the details screen subtitles and values to make it consistent, and make it look like what is in the designs.
    @Query("SELECT * from asteroids")
    fun getAsteroids(): Flow<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg asteroid: Asteroid)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asteroid: Asteroid)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asteroids: Collection<Asteroid>)

    @Delete
    suspend fun delete(asteroid: Asteroid)

    @Query("DELETE FROM asteroids")
    suspend fun deleteAll(): Int
}