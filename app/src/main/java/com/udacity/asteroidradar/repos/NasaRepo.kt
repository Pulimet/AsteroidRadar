package com.udacity.asteroidradar.repos

import com.udacity.asteroidradar.api.NasaApiService

class NasaRepo(private val nasaApiService: NasaApiService) {
    suspend fun getImageOfTheDay() = nasaApiService.getImageOfTheDay()
    suspend fun getAsteroidForToday() = nasaApiService.getAsteroidForToday()
}