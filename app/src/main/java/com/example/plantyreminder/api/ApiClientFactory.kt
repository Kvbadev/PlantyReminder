package com.example.plantyreminder.api

import com.example.plantyreminder.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClientFactory {

    private fun createApiClient(converterFactory: Converter.Factory): ApiClient {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        val keyInterceptor = Interceptor {
            val newUrl = it.request().url
                .newBuilder()
                .addQueryParameter("key", BuildConfig.PLANTS_API_KEY)
                .build()

            val req = it.request().newBuilder().url(newUrl).build()

            it.proceed(req)
        }

        val baseUrl = BuildConfig.PLANTS_API_BASE_URL
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(keyInterceptor)
            .build()

        val apiClient = ApiClient(
            client = httpClient,
            baseUrl = baseUrl,
            converterFactory = converterFactory,
        )
        apiClient.initializeClient()

        return apiClient
    }
    fun createGsonApiClient(): ApiClient {
        return createApiClient(GsonConverterFactory.create())
    }

    fun createScalarsApiClient(): ApiClient {
        return createApiClient(ScalarsConverterFactory.create());
    }

    fun createHttpErrorApiClient(): ApiClient {
        val converterFactory = GsonConverterFactory.create()

        val baseUrl = BuildConfig.PLANTS_API_BASE_URL
        val httpClient = OkHttpClient.Builder()
            .build()

        val apiClient = ApiClient(
            client = httpClient,
            baseUrl = baseUrl,
            converterFactory = converterFactory,
        )
        apiClient.initializeClient()

        return apiClient
    }
}