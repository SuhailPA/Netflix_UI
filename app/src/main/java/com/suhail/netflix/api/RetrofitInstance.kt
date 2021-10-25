package com.suhail.netflix.api

import android.util.Log
import com.suhail.netflix.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val retrofitApi: MoviesAPI by lazy {
        Log.i("Check","object")
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesAPI::class.java)
    }
}