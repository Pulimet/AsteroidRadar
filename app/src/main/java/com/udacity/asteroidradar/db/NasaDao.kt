package com.udacity.asteroidradar.db

import androidx.room.*
import com.udacity.asteroidradar.model.Asteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface NasaDao {
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