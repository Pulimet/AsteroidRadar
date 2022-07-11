package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.model.AsteroidsFeed
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") apiKey: String = Constants.API_KEY): PictureOfDay

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidForToday(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("start_date") startDate: String = "2015-09-07",
        @Query("end_date") endDate: String = "2015-09-08",
    ): AsteroidsFeed

}