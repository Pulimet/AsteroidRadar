package com.udacity.asteroidradar.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApiService
import com.udacity.asteroidradar.api.NetworkObjectsCreator
import com.udacity.asteroidradar.db.NasaDatabase
import com.udacity.asteroidradar.logs.OkHttpLogs
import com.udacity.asteroidradar.repos.NasaRepo
import com.udacity.asteroidradar.ui.main.MainViewModel
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object Di {
    fun setup(applicationContext: Context) {
        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }
    }

    private val appModule = module {
        // Network
        single<HttpLoggingInterceptor.Logger> { OkHttpLogs() }
        single { NetworkObjectsCreator.createOkHttpClient(get()) }
        single { NetworkObjectsCreator.createWebService<NasaApiService>(get(), Constants.BASE_URL) }

        // DataBase
        single {
            Room.databaseBuilder(androidContext(), NasaDatabase::class.java, "nasa_database").build()
        }
        single { get<NasaDatabase>().nasaDao() }

        // WorkManager
        single { WorkManager.getInstance(androidContext()) }

        // Repos
        singleOf(::NasaRepo)

        // ViewModels
        viewModelOf(::MainViewModel)
    }
}