package com.example.plantyreminder.views.search

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.api.ApiClient
import com.example.plantyreminder.api.ApiClientFactory
import com.example.plantyreminder.data.Plant
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val apiClientFactory: ApiClientFactory = ApiClientFactory()
    private val apiClient: ApiClient = apiClientFactory.createGsonApiClient()

    fun getPlants(): SnapshotStateList<Plant> {

        val plantsList = SnapshotStateList<Plant>()

        viewModelScope.launch {
            apiClient.getAll().onSuccess {
                plantsList.clear()
                plantsList.addAll(it);
            };
        }

        return plantsList;
    }

}
