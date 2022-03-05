package com.eman.jahezapp.data.repo

import com.eman.jahezapp.data.api.ApiService
import com.eman.jahezapp.domain.model.Restaurant
import com.eman.jahezapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRestaurantRepo @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getRestaurant(): Flow<Resource<List<Restaurant>>> {
        return flow {
            emit(Resource.loading(null))
            val resource = try {
                val restaurant = apiService.getRestaurant()
                Resource.success(restaurant)
            } catch (e: Throwable) {
                Resource.error(e.toString(), null)
            }
            emit(resource)
        }
    }
}