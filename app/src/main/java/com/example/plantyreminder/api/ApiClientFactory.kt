package com.example.plantyreminder.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClientFactory {

    fun createApiClient(): ApiClient {
        val converterFactory = ScalarsConverterFactory.create();
        val interceptor = HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        val key = "sk-gYv86422f2f97f00e373";
        val baseUrl = "https://perenual.com/";
        val httpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor {
                val req = it.request().newBuilder().addHeader("key", key).build();
                it.proceed(req)
            })
            .build();

        return ApiClient(
            client = httpClient,
            baseUrl = baseUrl,
            converterFactory = converterFactory,
        );
    }
}