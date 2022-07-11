package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.AsteroidNetwork

fun convertAsteroidData(list: List<AsteroidNetwork>) =
    list.map {
        Asteroid(
            it.id,
            it.name,
            it.closeApproachData[0].closeApproachDate,
            it.absoluteMagnitude,
            it.estimatedDiameter.kilometers.max,
            it.closeApproachData[0].relativeVelocity.kmPerSecond,
            it.closeApproachData[0].missDistance.astronomical,
            it.isPotentiallyHazardous
        )
    }

