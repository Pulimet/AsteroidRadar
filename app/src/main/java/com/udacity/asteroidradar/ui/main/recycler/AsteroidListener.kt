package com.udacity.asteroidradar.ui.main.recycler

import com.udacity.asteroidradar.model.Asteroid

interface AsteroidListener {
    fun onClick(asteroid: Asteroid)
}