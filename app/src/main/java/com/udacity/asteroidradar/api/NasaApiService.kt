package com.udacity.asteroidradar.api

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
    }

    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") apiKey: String = Constants.API_KEY): PictureOfDay

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidForToday(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("start_date") startDate: String = getTodayDate(),
        @Query("end_date") endDate: String = getTodayDate(),
    ): AsteroidsFeed

}