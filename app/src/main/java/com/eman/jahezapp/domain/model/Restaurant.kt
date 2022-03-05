package com.eman.jahezapp.domain.model

import com.squareup.moshi.Json


data class Restaurant(
    @Json(name = "id")
    var id: String = "",

    @Json(name = "name")
    var name: String = "",

    @Json(name = "image")
    val image: String = "",

    @Json(name = "hours")
    val hours: String = "",

    @Json(name = "description")
    val description: String = "",

    @Json(name = "rating")
    val rating: Float = 0f,

    @Json(name = "distance")
    val distance: Double = 0.0,

    @Json(name = "hasOffer")
    val hasOffer: Boolean =false,

    )