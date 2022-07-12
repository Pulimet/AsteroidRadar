package com.udacity.asteroidradar.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.udacity.asteroidradar.api.convertAsteroidData
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.repos.NasaRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val nasaRepo: NasaRepo) : ViewModel() {
    companion object {
        private const val MEDIA_TYPE_IMAGE = "image"
    }

    private val _navigate = MutableSharedFlow<NavDirections>()
    val navigate = _navigate.asSharedFlow()

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
                _asteroidsList.value = convertAsteroidData(asteroidsFeed.nearEarthObjects)
            }
            Log.d("Asteroids", "SIZE: ${asteroidsFeed.nearEarthObjects.size}")
        }
    }

    // User Actions
    fun onAsteroidClick(asteroid: Asteroid) {
        viewModelScope.launch {
            _navigate.emit(MainFragmentDirections.actionShowDetail(asteroid))
        }
    }
}