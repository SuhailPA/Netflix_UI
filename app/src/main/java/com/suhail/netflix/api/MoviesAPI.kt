package com.suhail.netflix.api

import com.suhail.netflix.dataClass.ResponceModel
import com.suhail.netflix.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("popular?")
    suspend fun getPopularMovies(@Query("api_key")apiKey: String= API_KEY):Response<ResponceModel>

    @GET("top_rated?")
    suspend fun getTopRatedMovies(@Query("api_key")
                                 apiKey:String=API_KEY):Response<ResponceModel>

    @GET("upcoming?")
    suspend fun getUpcomingMovies(@Query("api_key")
                                  apiKey:String=API_KEY):Response<ResponceModel>

    @GET("now_playing?")
    suspend fun getLatestMovie(@Query("api_key")
                                apiKey: String= API_KEY):Response<ResponceModel>

}