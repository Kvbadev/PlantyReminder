package com.example.plantyreminder.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClient(
    baseUrl: String,
    converterFactory: ScalarsConverterFactory,
    client: OkHttpClient
) {
    val apiInterface: ApiInterface

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(client)
            .build();

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

}