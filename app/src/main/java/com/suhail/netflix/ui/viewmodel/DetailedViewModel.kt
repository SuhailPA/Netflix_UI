package com.suhail.netflix.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suhail.netflix.dataClass.Result

class DetailedViewModel(var movieDet:Result):ViewModel() {

    val smallImage:MutableLiveData<String> = MutableLiveData()
    val biggerPoster: MutableLiveData<String> = MutableLiveData()
    val movieName : MutableLiveData<String> = MutableLiveData()
    val movieDesc : MutableLiveData<String> = MutableLiveData()

    init {
        setValues()
    }
    fun setValues(){
        smallImage.value=movieDet.posterPath
        biggerPoster.value=movieDet.backdropPath
        movieName.value =movieDet.originalTitle
        movieDesc.value = movieDet.overview
    }


}