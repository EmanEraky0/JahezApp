package com.eman.jahezapp.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eman.jahezapp.domain.model.Restaurant
import com.eman.jahezapp.domain.usecases.getMainRestaurantUseCase
import com.eman.jahezapp.utils.NetworkHelper
import com.eman.jahezapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor
    (private val mainRepositoryUseCase: getMainRestaurantUseCase, private val networkHelper: NetworkHelper) :
    ViewModel() {
    private val _restaurant = MutableLiveData<Resource<List<Restaurant>>>()

    val restaurant: MutableLiveData<Resource<List<Restaurant>>>
        get() = _restaurant


    fun getRestaurantResponse() {
        viewModelScope.launch {
            _restaurant.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepositoryUseCase.getRestaurant().collect {
                    _restaurant.postValue(it)
                }
            } else _restaurant.postValue(Resource.error("No internet connection", null))
        }
    }


}