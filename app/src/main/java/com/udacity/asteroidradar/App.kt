package com.udacity.asteroidradar

import android.app.Application
import com.udacity.asteroidradar.di.Di

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Di.setup(applicationContext)
    }
}