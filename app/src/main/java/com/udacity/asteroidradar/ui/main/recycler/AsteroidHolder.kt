package com.udacity.asteroidradar.ui.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.ListItemBinding
import com.udacity.asteroidradar.model.Asteroid

class AsteroidHolder(private var binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): AsteroidHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return AsteroidHolder(binding)
        }
    }

    fun bind(listener: AsteroidListener, asteroid: Asteroid) {
        binding.apply {
            this.asteroid = asteroid
            clickListener = listener
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            executePendingBindings()
        }
    }
}