package com.udacity.asteroidradar.db

import androidx.room.*
import com.udacity.asteroidradar.model.Asteroid
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface NasaDao {

    // TODO If you're submitting via a public Github repository, please make sure any external API key that you utilize, has been removed from your code.
    //  It's highly unsafe (and often breaks the Terms of Service) to include API keys in public repos, so you need to remove yours.
    //  You can add a note in a README file where a reviewer should go to insert their API key. Reviewers have been trained to expect this situation.
    // TODO Check the Project Rubric to confirm that you have met all of the requirements: https://review.udacity.com/#!/rubrics/2851/view

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