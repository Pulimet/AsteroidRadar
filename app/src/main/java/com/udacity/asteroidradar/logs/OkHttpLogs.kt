package com.udacity.asteroidradar.logs

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

@Suppress("ConstantConditionIf")
class OkHttpLogs : HttpLoggingInterceptor.Logger {
    companion object {
        const val TAG = "Asteroid"
    }

    override fun log(message: String) {
        Log.d(TAG, message)
    }
}