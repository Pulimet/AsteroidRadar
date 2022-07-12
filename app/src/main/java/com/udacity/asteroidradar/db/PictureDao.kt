package com.udacity.asteroidradar.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.model.PictureOfDay
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface PictureDao {

    @Query("SELECT * FROM pictures WHERE id = 0")
    fun getPicture(): Flow<PictureOfDay>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureOfDay: PictureOfDay)
}