package com.udacity.asteroidradar

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

// Short way to collect StateFlow on coroutine when Activity Started.
// Useful when you need to collect one StateFlow, but could be used for many.
fun <T> Flow<T>.collectIt(lifecycleOwner: LifecycleOwner, function: (T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collect {
                function.invoke(it)
            }
        }
    }
}