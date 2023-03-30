package com.example.plantyreminder.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/species-list?page=1")
    suspend fun getAll(@Query("q") query: String=""): DataNetObject
}