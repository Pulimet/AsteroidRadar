package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.AsteroidNetwork

fun convertAsteroidData(list: Map<String, List<AsteroidNetwork>>): List<Asteroid> {
    val asteroidsList = mutableListOf<Asteroid>()
    list.forEach {
        asteroidsList.addAll(it.value.map { asteroid ->
            Asteroid(
                asteroid.id,
                asteroid.name,
                it.key,
                asteroid.absoluteMagnitude,
                asteroid.estimatedDiameter.kilometers.max,
                asteroid.closeApproachData[0].relativeVelocity.kmPerSecond,
                asteroid.closeApproachData[0].missDistance.astronomical,
                asteroid.isPotentiallyHazardous
            )
        })
    }
    return asteroidsList
}

