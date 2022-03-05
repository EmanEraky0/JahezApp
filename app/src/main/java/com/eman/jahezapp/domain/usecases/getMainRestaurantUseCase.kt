package com.eman.jahezapp.domain.usecases

import com.eman.jahezapp.data.repo.ApiHelper
import javax.inject.Inject

class getMainRestaurantUseCase @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getRestaurant() =
        apiHelper.getRestaurant()


}