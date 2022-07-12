package com.udacity.asteroidradar.logs

import android.util.Log
import com.udacity.asteroidradar.App
import okhttp3.logging.HttpLoggingInterceptor

@Suppress("ConstantConditionIf")
class OkHttpLogs : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.d(App.TAG, message)
    }
}