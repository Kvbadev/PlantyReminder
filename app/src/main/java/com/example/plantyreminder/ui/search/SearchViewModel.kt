package com.example.plantyreminder.ui.search

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.data.api.ApiClientFactory
import com.example.plantyreminder.data.dto.PlantSearchResult
import com.example.plantyreminder.domain.DataState
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantTimespan
import com.example.plantyreminder.domain.SuspendedResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val apiClient: ApiClient,
) : ViewModel() {

    private val dataState: DataState<List<PlantSearchResult>> = DataState(
        data = MutableStateFlow(emptyList()),
        error = MutableStateFlow(null),
        loading = MutableStateFlow(false),
    )

    val loadingState = dataState.loading.asStateFlow()
    val results = dataState.data.asStateFlow()
    val errorState = dataState.error.asStateFlow()

    fun getSearchResults(query: String) {
        dataState.loading.update { true }
        viewModelScope.launch {
            when (val res = apiClient.getAll(query)) {
                is SuspendedResult.Success -> dataState.data.update { res.data }
                is SuspendedResult.Error -> dataState.error.update { res.error }
            };
            dataState.loading.update { false }
        }
    }

}
