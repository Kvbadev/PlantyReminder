package com.example.plantyreminder.api

import com.example.plantyreminder.data.models.Plant
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @Headers("Content-Type: text/html")
    @GET("api/species-list?page=1&key=sk-gYv86422f2f97f00e373")
    suspend fun getAll(): String
}