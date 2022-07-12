package com.udacity.asteroidradar.workers

import android.content.Context
import android.util.Log
import androidx.work.*
import com.udacity.asteroidradar.App
import com.udacity.asteroidradar.api.convertAsteroidData
import com.udacity.asteroidradar.db.NasaDao
import com.udacity.asteroidradar.db.PictureDao
import com.udacity.asteroidradar.repos.NasaRepo
import com.udacity.asteroidradar.ui.main.MainViewModel
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

    private val nasaRepo: NasaRepo by inject()
    private val nasaDao: NasaDao by inject()
    private val pictureDao: PictureDao by inject()

    override suspend fun doWork() = try {
        Log.d(App.TAG, " doWork() launched")
        fetchAndSaveToDbAsteroidsFeed()
        fetchAndSaveToDbImageOfTheDay()
        deleteOldAsteroids()
        Log.d(App.TAG, " doWork() Success")
        Result.success()
    } catch (e: Exception) {
        Log.d(App.TAG, " doWork() Failed")
        Result.retry()
    }

    private suspend fun fetchAndSaveToDbAsteroidsFeed() {
        val asteroidsFeed = nasaRepo.getAsteroidForToday()
        if (asteroidsFeed.nearEarthObjects.isNotEmpty()) {
            nasaDao.insertAll(convertAsteroidData(asteroidsFeed.nearEarthObjects))
        }
    }

    private suspend fun fetchAndSaveToDbImageOfTheDay() {
        nasaRepo.getImageOfTheDay().apply {
            if (mediaType == MainViewModel.MEDIA_TYPE_IMAGE) {
                pictureDao.insertPicture(this)
            }
        }
    }

    private suspend fun deleteOldAsteroids() {
        nasaDao.deleteOldAsteroids()
    }

}