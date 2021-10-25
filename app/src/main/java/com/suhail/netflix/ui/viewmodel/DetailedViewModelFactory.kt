package com.suhail.netflix.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suhail.netflix.dataClass.Result

class DetailedViewModelFactory(var movieDet:Result):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailedViewModel(movieDet) as T
    }
}