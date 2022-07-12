package com.udacity.asteroidradar.db

import androidx.room.*
import com.udacity.asteroidradar.model.Asteroid
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface NasaDao {

    @Query("SELECT * FROM asteroids WHERE date >= :fromDate ORDER BY date")
    fun getAsteroids(fromDate: Date = Date()): Flow<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroids(vararg asteroid: Asteroid)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asteroids: Collection<Asteroid>)

    @Delete
    suspend fun deleteAsteroid(asteroid: Asteroid)

    @Query("DELETE FROM asteroids")
    suspend fun deleteAllAsteroids()

    @Query("DELETE FROM asteroids WHERE date < :untilDate")
    suspend fun deleteOldAsteroids(untilDate: Date = Date())
}