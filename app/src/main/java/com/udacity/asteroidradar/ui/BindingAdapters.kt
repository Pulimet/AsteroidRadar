package com.udacity.asteroidradar.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.ui.main.recycler.AsteroidAdapter

// Image of the day
@BindingAdapter("imageOfTheDay")
fun bindImageOfTheDay(imageView: ImageView, url: String?) {
    url?.let { Picasso.get().load(it).into(imageView) }
}

@BindingAdapter("imageOfTheDayDescription")
fun bindImageOfTheDayDescription(imageView: ImageView, description: String?) {
    description?.let { imageView.contentDescription = it }
}

// RecyclerView
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>) {
    val adapter = recyclerView.adapter as AsteroidAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0) // scroll to the top after the diffs calculate
    }
}

@BindingAdapter("showOnlyWhenEmpty")
fun View.showOnlyWhenEmpty(data: List<Asteroid>?) {
    visibility = when {
        data == null || data.isEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}

// List Item
@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
