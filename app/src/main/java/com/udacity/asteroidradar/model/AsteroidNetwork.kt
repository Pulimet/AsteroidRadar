package com.udacity.asteroidradar.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsteroidNetwork(
    val id: Long,
    val name: String,
    @SerializedName("absolute_magnitude_h") val absoluteMagnitude: Double,
    @SerializedName("estimated_diameter") val estimatedDiameter: EstimatedDiameter,
    @SerializedName("close_approach_data") val closeApproachData: List<CloseApproachData>,
    @SerializedName("is_potentially_hazardous_asteroid") val isPotentiallyHazardous: Boolean
) : Parcelable

@Parcelize
data class EstimatedDiameter(val kilometers: Kilometers) : Parcelable

@Parcelize
data class Kilometers(@SerializedName("estimated_diameter_max") val max: Double) : Parcelable

@Parcelize
data class CloseApproachData(
    @SerializedName("relative_velocity") val relativeVelocity: RelativeVelocity,
    @SerializedName("miss_distance") val missDistance: MissDistance,
    @SerializedName("close_approach_date") val closeApproachDate: String
) : Parcelable

@Parcelize
data class RelativeVelocity(@SerializedName("kilometers_per_second") val kmPerSecond: Double) : Parcelable

@Parcelize
data class MissDistance(val astronomical: Double) : Parcelable
