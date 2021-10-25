package com.suhail.netflix.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhail.netflix.dataClass.ResponceModel
import com.suhail.netflix.repository.MoviesRepository
import com.suhail.netflix.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val moviesRepository: MoviesRepository):ViewModel() {

    val popularMovies:MutableLiveData<Resource<ResponceModel>> = MutableLiveData()
    val topRatedMovies:MutableLiveData<Resource<ResponceModel>> = MutableLiveData()
    val upcomingMovies:MutableLiveData<Resource<ResponceModel>> = MutableLiveData()
    val latestMovie:MutableLiveData<Resource<ResponceModel>> = MutableLiveData()
    init {
        Log.i("Checking","Conencted")
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
        getLatestMovie()
    }
    private fun getLatestMovie()= viewModelScope.launch {
        latestMovie.postValue(Resource.Loading())
        val response=moviesRepository.getLatestMovies()
        latestMovie.postValue(handleResponceCheck(response))
    }
    private fun getTopRatedMovies()=viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val responce=moviesRepository.getTopRatedMovies()
        topRatedMovies.postValue(handleResponceCheck(responce))
    }

    private fun getPopularMovies()=viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val responce=moviesRepository.getPopularMovies()
        popularMovies.postValue(handleResponceCheck(responce))
    }
    private fun getUpcomingMovies()=viewModelScope.launch {
        upcomingMovies.postValue(Resource.Loading())
        val response=moviesRepository.getUpcomingMovies()
        upcomingMovies.postValue(handleResponceCheck(response))

    }

    private fun handleResponceCheck(responce: Response<ResponceModel>): Resource<ResponceModel> {
        if (responce.isSuccessful){
            Log.i("Check","success")
            responce.body()?.let { resultResponce->
                return Resource.Success(resultResponce)
            }
        }else{
            Log.i("Check","failure")
        }
            return Resource.Error(responce.message())

    }

}