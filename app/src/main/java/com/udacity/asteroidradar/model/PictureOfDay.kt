package com.udacity.asteroidradar.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "pictures")
data class PictureOfDay(
    @PrimaryKey val id: Long = 0,
    @SerializedName("media_type")
    val mediaType: String,
    val title: String,
    val url: String
) : Parcelable