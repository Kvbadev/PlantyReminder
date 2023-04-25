package com.example.plantyreminder

import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.data.api.ApiClientFactory
import com.example.plantyreminder.domain.ErrorEntity
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.SuspendedResult
import kotlinx.coroutines.runBlocking

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import retrofit2.HttpException
import java.util.UUID

class ApiClientUnitTest {
    private val apiClientFactory: ApiClientFactory = ApiClientFactory()

    @Test
    fun getAllIsCorrectResponse(): Unit = runBlocking {

        val apiClient: ApiClient = apiClientFactory.createGsonApiClient()
        when(val response = apiClient.getAll()) {
            is SuspendedResult.Success -> {
                assertNotEquals(0, response.data.size) {"Size of the list is 0"}
                assertTrue(response.data.first()::class.java == PlantSearchResult::class.java) {"Elements are not of type Plant"}
            }
            is SuspendedResult.Error -> assertEquals(Exception::class.java, response.error ::class.java) { "Returned value is not HttpException" }
        }
    }

    @Test
    fun getAllNotFoundException(): Unit = runBlocking {
        val apiClient = apiClientFactory.createHttpErrorApiClient()

        val failedCall = apiClient.getAll() as SuspendedResult.Error
        assert(failedCall.error is ErrorEntity.Network) { "Returned value is not network exception" }

    }

    @Test
    fun getEmptyList(): Unit = runBlocking {
        val apiClient: ApiClient = apiClientFactory.createGsonApiClient()
        val response = apiClient.getAll(UUID.randomUUID().toString()) as SuspendedResult.Success
        assertTrue(response.data.isEmpty())
    }
}