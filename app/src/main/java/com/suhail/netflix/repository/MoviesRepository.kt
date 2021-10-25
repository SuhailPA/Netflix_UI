package com.suhail.netflix.repository

import android.util.Log
import com.suhail.netflix.api.RetrofitInstance
import com.suhail.netflix.dataClass.ResponceModel

import retrofit2.Response

class MoviesRepository {
    suspend fun getPopularMovies() : Response<ResponceModel> = RetrofitInstance.retrofitApi.getPopularMovies()

    suspend fun getTopRatedMovies() : Response<ResponceModel> = RetrofitInstance.retrofitApi.getTopRatedMovies()

    suspend fun getUpcomingMovies() : Response<ResponceModel> = RetrofitInstance.retrofitApi.getUpcomingMovies()

    suspend fun getLatestMovies() : Response<ResponceModel> = RetrofitInstance.retrofitApi.getLatestMovie()
}