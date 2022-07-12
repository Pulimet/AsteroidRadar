package com.udacity.asteroidradar.ui.main.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.udacity.asteroidradar.model.Asteroid

class AsteroidAdapter(private val listener: AsteroidListener) :
    ListAdapter<Asteroid, AsteroidHolder>(AsteroidDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidHolder {
        return AsteroidHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AsteroidHolder, position: Int) {
        holder.bind(listener, getItem(position))
    }
}
