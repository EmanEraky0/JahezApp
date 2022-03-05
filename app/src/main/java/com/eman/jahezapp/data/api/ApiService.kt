package com.eman.jahezapp.data.api

import com.eman.jahezapp.domain.model.Restaurant
import retrofit2.http.GET

interface ApiService {
    @GET("restaurants.json")
    suspend fun getRestaurant(): List<Restaurant>





}