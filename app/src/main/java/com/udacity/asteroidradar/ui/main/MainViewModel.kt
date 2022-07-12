package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.udacity.asteroidradar.api.convertAsteroidData
import com.udacity.asteroidradar.api.retryIO
import com.udacity.asteroidradar.db.NasaDao
import com.udacity.asteroidradar.db.PictureDao
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.repos.NasaRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val nasaRepo: NasaRepo,
    private val nasaDao: NasaDao,
    private val pictureDao: PictureDao
) : ViewModel() {

    companion object {
        const val MEDIA_TYPE_IMAGE = "image"
    }

    private val _navigate = MutableSharedFlow<NavDirections>()
    val navigate = _navigate.asSharedFlow()

    val pictureOfDay =
        pictureDao.getPicture().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PictureOfDay(0, "", "", ""))
    val asteroidsList = nasaDao.getAsteroids().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        getImageOfTheDay()
        getAsteroidForToday()
    }

    private fun getImageOfTheDay() {
        viewModelScope.launch(Dispatchers.IO) {
            retryIO(desc = "Get Image of the day") { nasaRepo.getImageOfTheDay() }?.apply {
                if (mediaType == MEDIA_TYPE_IMAGE) {
                    pictureDao.insertPicture(this)
                }
            }
        }
    }

    private fun getAsteroidForToday() {
        viewModelScope.launch(Dispatchers.IO) {
            retryIO(desc = "Get Asteroids Feed") { nasaRepo.getAsteroidForToday() }?.apply {
                if (nearEarthObjects.isNotEmpty()) {
                    nasaDao.insertAll(convertAsteroidData(nearEarthObjects))
                }
            }
        }
    }

    // User Actions
    fun onAsteroidClick(asteroid: Asteroid) {
        viewModelScope.launch {
            _navigate.emit(MainFragmentDirections.actionShowDetail(asteroid))
        }
    }
}