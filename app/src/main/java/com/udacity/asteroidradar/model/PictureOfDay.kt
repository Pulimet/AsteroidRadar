package com.udacity.asteroidradar.model

import com.google.gson.annotations.SerializedName


data class PictureOfDay(
    @SerializedName("media_type") val mediaType: String,
    val title: String,
    val url: String
)