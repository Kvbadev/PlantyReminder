package com.example.plantyreminder.api

import com.example.plantyreminder.api.dto.ApiPlantObject
import com.example.plantyreminder.api.dto.ApiPlantObjectList
import com.example.plantyreminder.data.models.Plant
import com.example.plantyreminder.utils.toPlant
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Retrofit

class ApiClient(
    private val baseUrl: String,
    private val converterFactory: Converter.Factory,
    private val client: OkHttpClient
) {
    private lateinit var apiInterface: ApiInterface;

    fun initializeClient() {

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build();

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    suspend fun getAll(predicate: String = ""): Result<List<Plant>> {
        val data: ApiPlantObjectList?

        try {
            data = apiInterface.getAll(predicate)
        } catch (e: HttpException) {
            return Result.failure(e)
        }

        return Result.success(data.results.map {
            it.toPlant();
        })
    }

}