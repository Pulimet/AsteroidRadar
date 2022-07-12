package com.udacity.asteroidradar.workers

import android.content.Context
import android.util.Log
import androidx.work.*
import com.udacity.asteroidradar.App
import com.udacity.asteroidradar.api.convertAsteroidData
import com.udacity.asteroidradar.db.NasaDao
import com.udacity.asteroidradar.repos.NasaRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class NasaWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params), KoinComponent {

    companion object {
        private const val WORK_NAME_FETCH_ASTEROIDS = "WORK_NAME_FETCH_ASTEROIDS"

        fun launch(workManager: WorkManager) {
            Log.d(App.TAG, "WorkManager.enqueueUniquePeriodicWork(...)")
            workManager.enqueueUniquePeriodicWork(
                WORK_NAME_FETCH_ASTEROIDS,
                ExistingPeriodicWorkPolicy.KEEP,
                getPeriodicWorkRequest()
            )
        }

        private fun getPeriodicWorkRequest() = PeriodicWorkRequestBuilder<NasaWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(30, TimeUnit.SECONDS)
            .setConstraints(getConstraints())
            .build()

        private fun getConstraints() = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()
    }

    private val moviesRepo: NasaRepo by inject()
    private val nasaDao: NasaDao by inject()

    override suspend fun doWork() = try {
        Log.d(App.TAG, " doWork() launched")
        fetchAndSaveToDbAsteroidsFeed()
        fetchAndSaveToDbImageOfTheDay()
        Log.d(App.TAG, " doWork() Success")
        Result.success()
    } catch (e: Exception) {
        Log.d(App.TAG, " doWork() Failed")
        Result.retry()
    }

    private suspend fun fetchAndSaveToDbImageOfTheDay() {
        moviesRepo.getImageOfTheDay()  // TODO save result to DB + Load url from DB + Preload image with picasso
    }

    private suspend fun fetchAndSaveToDbAsteroidsFeed() {
        val asteroidsFeed = moviesRepo.getAsteroidForToday()
        if (asteroidsFeed.nearEarthObjects.isNotEmpty()) {
            nasaDao.insertAll(convertAsteroidData(asteroidsFeed.nearEarthObjects))
        }
    }

}