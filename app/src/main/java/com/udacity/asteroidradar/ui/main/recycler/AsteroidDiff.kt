package com.udacity.asteroidradar.ui.main.recycler

import androidx.recyclerview.widget.DiffUtil
import com.udacity.asteroidradar.model.Asteroid

class AsteroidDiff : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid) = oldItem == newItem
}