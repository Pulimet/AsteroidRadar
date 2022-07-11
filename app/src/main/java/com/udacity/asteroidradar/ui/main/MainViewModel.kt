package com.udacity.asteroidradar.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.api.convertAsteroidData
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.repos.NasaRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val nasaRepo: NasaRepo) : ViewModel() {
    companion object {
        private const val MEDIA_TYPE_IMAGE = "image"
    }

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl = _imageUrl.asStateFlow()

    private val _imageDescription = MutableStateFlow<String?>(null)
    val imageDescription = _imageDescription.asStateFlow()

    private val _asteroidsList = MutableStateFlow<List<Asteroid>>(emptyList())
    val asteroidsList = _asteroidsList.asStateFlow()

    init {
        getImageOfTheDay()
        getAsteroidForToday()
    }

    private fun getImageOfTheDay() {
        viewModelScope.launch(Dispatchers.IO) {
            val pictureOfDay = nasaRepo.getImageOfTheDay()
            if (pictureOfDay.mediaType == MEDIA_TYPE_IMAGE) {
                _imageUrl.value = pictureOfDay.url
                _imageDescription.value = pictureOfDay.title
            }
        }
    }

    private fun getAsteroidForToday() {
        viewModelScope.launch(Dispatchers.IO) {
            val asteroidsFeed = nasaRepo.getAsteroidForToday()
            if (asteroidsFeed.nearEarthObjects.isNotEmpty()) {
                val rawList = asteroidsFeed.nearEarthObjects.entries.first().value
                _asteroidsList.value = convertAsteroidData(rawList)
            }
            Log.d("Asteroids", "SIZE: ${asteroidsFeed.nearEarthObjects.size}")
        }
    }
}