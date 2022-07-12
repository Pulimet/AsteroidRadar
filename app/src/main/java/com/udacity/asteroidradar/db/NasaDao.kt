package com.udacity.asteroidradar.db

import androidx.room.*
import com.udacity.asteroidradar.model.Asteroid
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface NasaDao {

    // TODO Make sure the entire app works without an internet connection. (Picture of the day for example)
    // TODO Modify the app to support multiple languages, device sizes, and orientations.
    // TODO Make the app delete asteroids from the previous day once a day using the same workManager that downloads the asteroids.
    // TODO Match the styles for the details screen subtitles and values to make it consistent, and make it look like what is in the designs.
    // TODO If you're submitting via a public Github repository, please make sure any external API key that you utilize, has been removed from your code.
    //  It's highly unsafe (and often breaks the Terms of Service) to include API keys in public repos, so you need to remove yours.
    //  You can add a note in a README file where a reviewer should go to insert their API key. Reviewers have been trained to expect this situation.
    // TODO Check the Project Rubric to confirm that you have met all of the requirements: https://review.udacity.com/#!/rubrics/2851/view

    @Query("SELECT * FROM asteroids WHERE date >= :fromDate ORDER BY date")
    fun getAsteroids(fromDate: Date = Date()): Flow<List<Asteroid>>

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