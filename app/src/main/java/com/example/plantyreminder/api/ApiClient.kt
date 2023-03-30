package com.example.plantyreminder.api

import com.example.plantyreminder.data.models.Plant
import com.example.plantyreminder.utils.toPlant
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class ApiClient(
    baseUrl: String,
    converterFactory: Converter.Factory,
    client: OkHttpClient
) {
    private val apiInterface: ApiInterface

    init {

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build();

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    suspend fun getAll(predicate: String=""): List<Plant> {
        val result = apiInterface.getAll(predicate);

        return result.data.map {
            it.toPlant();
        }
    }

}