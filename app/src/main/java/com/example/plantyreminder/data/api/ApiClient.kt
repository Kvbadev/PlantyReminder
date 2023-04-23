package com.example.plantyreminder.data.api

import com.example.plantyreminder.data.dto.PlantSearchResult
import com.example.plantyreminder.domain.ErrorEntity
import com.example.plantyreminder.domain.SuspendedResult
import com.example.plantyreminder.utils.toPlantSearchResult
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
            SuspendedResult.Error(ErrorEntity.Network.InvalidHttpResponse)
        } catch (e: Exception) {
            val error = ErrorEntity.Default.Unknown
            error.message = e.message!!
            SuspendedResult.Error(error)
        }
    }
}