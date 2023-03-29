package com.example.plantyreminder.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.api.ApiClient
import com.example.plantyreminder.api.ApiClientFactory
import com.example.plantyreminder.api.ApiInterface
import com.example.plantyreminder.data.models.Plant
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeViewModel : ViewModel() {

    private var apiClient: ApiClient;

    init {
        val apiClientFactory = ApiClientFactory();
        apiClient = apiClientFactory.createApiClient()
    }

    fun getPlants() {

        viewModelScope.launch {
            try {
                val data = apiClient.apiInterface.getAll();
                data.indexOf("p");
            } catch (e: Exception) {
                print(e.message);
            }
        }

    }

}