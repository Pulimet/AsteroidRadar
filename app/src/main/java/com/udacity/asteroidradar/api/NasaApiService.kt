package com.udacity.asteroidradar.api

import android.icu.util.Calendar
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.model.AsteroidsFeed
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface NasaApiService {
    companion object {
        private fun getTodayDate(): String =
            SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.US).format(Date())

        private fun getNextWeekDate(): String {
            val calendar = Calendar.getInstance()
            calendar.add(java.util.Calendar.DAY_OF_YEAR, 6)
            val currentTime = calendar.time
            return SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.US).format(currentTime)
        }
    }

    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") apiKey: String = Constants.API_KEY): PictureOfDay

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidForToday(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("start_date") startDate: String = getTodayDate(),
        @Query("end_date") endDate: String = getNextWeekDate(),
    ): AsteroidsFeed

}