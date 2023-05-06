package com.example.plantyreminder.data.api

import android.util.Log
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.data.dto.ApiPlantObject
import com.example.plantyreminder.domain.ErrorEntity
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.SuspendedResult
import com.example.plantyreminder.utils.toPlant
import com.example.plantyreminder.utils.toPlantSearchResult
import com.google.gson.JsonSyntaxException
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

    suspend fun getAll(predicate: String = ""): SuspendedResult<List<PlantSearchResult>> {
        return try {
            val data = apiInterface.getAll(predicate).results.map {
                it.toPlantSearchResult()
            }
            SuspendedResult.Success(data)
        } catch (e: HttpException) {
//            Log.e("GetAll", e.message.toString())
            SuspendedResult.Error(ErrorEntity.Network.InvalidHttpResponse)
        }
    }

    suspend fun getPlant(id: Int): SuspendedResult<Plant> {
        return try {
            val data = apiInterface.getPlant(id).toPlant()
            SuspendedResult.Success(data)
        } catch (e: HttpException) {
            Log.e("GetPlant", e.message.toString())
            SuspendedResult.Error(ErrorEntity.Network.InvalidHttpResponse)
        }
    }
}