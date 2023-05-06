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

class ApiClientUnitTest(
) {
    private val apiClientFactory: ApiClientFactory = ApiClientFactory()
    private val apiClientGson = apiClientFactory.createGsonApiClient()
    private val apiClientInvalid = apiClientFactory.createHttpErrorApiClient()

    @Test
    fun getAllIsCorrectResponse(): Unit = runBlocking {

        when(val response = apiClientGson.getAll()) {
            is SuspendedResult.Success -> {
                assertNotEquals(0, response.data.size) {"Size of the list is 0"}
                assertTrue(response.data.first()::class.java == PlantSearchResult::class.java) {"Elements are not of type Plant"}
            }
            is SuspendedResult.Error -> assertEquals(Exception::class.java, response.error ::class.java) { "Returned value is not HttpException" }
        }
    }

    @Test
    fun getAllWithQueryIsCorrectResponse(): Unit = runBlocking {
        when(val response = apiClientGson.getAll("ah")) {
            is SuspendedResult.Success -> {
                assertNotEquals(0, response.data.size) {"Size of the list is 0"}
                assertTrue(response.data.first()::class.java == PlantSearchResult::class.java) {"Elements are not of type Plant"}
            }
            is SuspendedResult.Error -> assertEquals(Exception::class.java, response.error ::class.java) { "Returned value is not HttpException" }
        }
    }

    @Test
    fun getAllNotFoundException(): Unit = runBlocking {
        val failedCall = apiClientInvalid.getAll() as SuspendedResult.Error
        assert(failedCall.error is ErrorEntity.Network) { "Returned value is not network exception" }

    }

    @Test
    fun getEmptyList(): Unit = runBlocking {
        val response = apiClientGson.getAll(UUID.randomUUID().toString()) as SuspendedResult.Success
        assertTrue(response.data.isEmpty())
    }
}