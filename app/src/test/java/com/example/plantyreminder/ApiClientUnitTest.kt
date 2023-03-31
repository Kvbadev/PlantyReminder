package com.example.plantyreminder

import com.example.plantyreminder.api.ApiClient
import com.example.plantyreminder.api.ApiClientFactory
import com.example.plantyreminder.data.models.Plant
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiClientUnitTest(
    private val apiClientFactory: ApiClientFactory = ApiClientFactory()
) {
    @Test
    fun getAll_isCorrectResponse() = runBlocking {

        val apiClient: ApiClient = apiClientFactory.createGsonApiClient()
        val response = apiClient.getAll()

        assertNotEquals(0, response.size)
        assertTrue(response.first()::class.java == Plant::class.java)
    }

    fun getAll_NotFoundException() = runBlocking {
        val apiClient = apiClientFactory.createHttpErrorApiClient()
        val response = apiClient.getAll();
    }
}