package com.example.plantyreminder.data.api

import com.example.plantyreminder.data.dto.ApiPlantObject
import com.example.plantyreminder.data.dto.ApiSearchResultObjectList
import com.example.plantyreminder.domain.Plant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/species-list?page=1")
    suspend fun getAll(@Query("q") query: String="") : ApiSearchResultObjectList

    @GET("api/species/details/{id}")
    suspend fun getPlant(@Path("id") id: Int): ApiPlantObject
}
