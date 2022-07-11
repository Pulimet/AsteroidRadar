package com.udacity.asteroidradar.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkObjectsCreator {
    fun createOkHttpClient(logger: HttpLoggingInterceptor.Logger) = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor(logger).apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()


    inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(T::class.java)
    }
}