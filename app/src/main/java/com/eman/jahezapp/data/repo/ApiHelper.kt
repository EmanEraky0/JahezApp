package com.eman.jahezapp.data.repo

import com.eman.jahezapp.domain.model.Restaurant
import com.eman.jahezapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getRestaurant(): Flow<Resource<List<Restaurant>>>




}