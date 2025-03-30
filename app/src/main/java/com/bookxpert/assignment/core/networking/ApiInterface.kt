package com.bookxpert.assignment.core.networking

import com.bookxpert.assignment.core.constants.ApiEndPoints
import com.bookxpert.assignment.home.data.Objects
import retrofit2.http.GET

interface ApiInterface {

    @GET(ApiEndPoints.OBJECTS)
    suspend fun getObjects(): List<Objects>

}