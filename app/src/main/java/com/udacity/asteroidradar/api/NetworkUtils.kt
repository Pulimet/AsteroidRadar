package com.udacity.asteroidradar.api

import android.util.Log
import com.udacity.asteroidradar.App
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.AsteroidNetwork
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

fun convertAsteroidData(list: Map<String, List<AsteroidNetwork>>): List<Asteroid> {
    val asteroidsList = mutableListOf<Asteroid>()
    val simpleDateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.US)
    list.forEach {
        asteroidsList.addAll(it.value.map { asteroid ->
            Asteroid(
                asteroid.id,
                asteroid.name,
                simpleDateFormat.parse(it.key) ?: Date(),
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

suspend fun <T> retryIO(
    times: Int = 4,
    initialDelay: Long = 1000, // 1 second
    maxDelay: Long = 4000,    // 4 seconds
    factor: Double = 2.0,
    desc: String = "",
    block: suspend () -> T?
): T? {
    var currentDelay = initialDelay
    repeat(times) {
        try {
            return block()
        } catch (e: IOException) {
            Log.e(App.TAG, "Description (IOException): $desc, fail counter: ${it + 1}, Exception: ${e.message}")
        } catch (e: HttpException) {
            Log.e(App.TAG, "Description (HttpException): $desc, fail counter: ${it + 1}, Exception: ${e.message} ")
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return null
}



