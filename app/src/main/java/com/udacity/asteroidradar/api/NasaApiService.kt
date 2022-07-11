package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("planetary/apod")
    suspend fun getImageOfTheDay(@Query("api_key") apiKey: String = Constants.API_KEY): PictureOfDay
}