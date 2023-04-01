package com.example.plantyreminder.views

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.api.ApiClient
import com.example.plantyreminder.api.ApiClientFactory
import com.example.plantyreminder.data.models.Plant
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private var apiClient: ApiClient;
    private var plantsList = SnapshotStateList<Plant>();

    init {
        val apiClientFactory = ApiClientFactory();
        apiClient = apiClientFactory.createGsonApiClient()
    }

    fun getPlants(): SnapshotStateList<Plant> {
        viewModelScope.launch {
            val allPlants = apiClient.getAll();

            plantsList.clear();
            plantsList.addAll(allPlants);
        }

        return plantsList;
    }

}
