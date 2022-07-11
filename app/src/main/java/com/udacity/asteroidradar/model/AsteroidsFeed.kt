package com.udacity.asteroidradar.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsteroidsFeed(
    @SerializedName("near_earth_objects") val nearEarthObjects: Map<String, List<AsteroidNetwork>>
) : Parcelable