package com.udacity.asteroidradar.di

import android.content.Context
import com.udacity.asteroidradar.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

object Di {
    fun setup(applicationContext: Context) {
        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }
    }

    private val appModule = module {

        // ViewModels
        viewModelOf(::MainViewModel)
    }
}