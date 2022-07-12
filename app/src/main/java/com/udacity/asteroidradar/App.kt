package com.udacity.asteroidradar

import android.app.Application
import androidx.work.WorkManager
import com.udacity.asteroidradar.di.Di
import com.udacity.asteroidradar.workers.NasaWorker
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class App : Application(), KoinComponent {

    companion object {
        const val TAG = "Asteroid"
    }

    private val workManager: WorkManager by inject()

    override fun onCreate() {
        super.onCreate()
        Di.setup(applicationContext)
        NasaWorker.launch(workManager)
    }
}