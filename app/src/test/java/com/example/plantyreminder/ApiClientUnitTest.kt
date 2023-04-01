package com.example.plantyreminder

import com.example.plantyreminder.api.ApiClient
import com.example.plantyreminder.api.ApiClientFactory
import com.example.plantyreminder.data.models.Plant
import kotlinx.coroutines.runBlocking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import retrofit2.HttpException
import java.util.UUID

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiClientUnitTest {
    private val apiClientFactory: ApiClientFactory = ApiClientFactory()

    @Test
    fun `getAll is Correct Response`(): Unit = runBlocking {

        val apiClient: ApiClient = apiClientFactory.createGsonApiClient()
        val response = apiClient.getAll()

        response.onSuccess {
            assertNotEquals(0, it.size) {"Size of the list is 0"}
            assertTrue(it.first()::class.java == Plant::class.java) {"Elements are not of type Plant"}
        }.onFailure {
            assertEquals(Exception::class.java, it::class.java) { "Returned value is not HttpException" }
        }
    }

    @Test
    fun `getAll Not Found Exception`(): Unit = runBlocking {
        val apiClient = apiClientFactory.createHttpErrorApiClient()

        apiClient.getAll().onFailure {
            assertEquals(HttpException::class.java, it::class.java) { "Returned value is not HttpException" }
        }

    }

    @Test
    fun `Get Empty List`(): Unit = runBlocking {
        val apiClient: ApiClient = apiClientFactory.createGsonApiClient()
        val response = apiClient.getAll(UUID.randomUUID().toString())

        response.onSuccess {
            assertTrue(it.isEmpty())
        }
    }
}