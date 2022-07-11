package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.repos.NasaRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val nasaRepo: NasaRepo) : ViewModel() {

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl = _imageUrl.asStateFlow()

    private val _imageDescription = MutableStateFlow<String?>(null)
    val imageDescription = _imageDescription.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val pictureOfDay = nasaRepo.getImageOfTheDay()
            _imageUrl.value = pictureOfDay.url
        }
    }
}