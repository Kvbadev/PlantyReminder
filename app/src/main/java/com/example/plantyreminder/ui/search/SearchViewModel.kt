package com.example.plantyreminder.ui.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.domain.DataState
import com.example.plantyreminder.domain.SuspendedResult
import com.example.plantyreminder.utils.debounce
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val apiClient: ApiClient,
) : ViewModel() {
    private val dataState: DataState<List<PlantSearchResult>> =
        DataState(data = MutableStateFlow(emptyList()))

    val loadingState = dataState.loading.asStateFlow()
    val results = dataState.data.asStateFlow()
    val errorState = dataState.error.asStateFlow()
    val query = mutableStateOf("")

    val debouncedSearch = debounce<String>(300L, viewModelScope) {
        viewModelScope.launch {
            when (val res = apiClient.getAll(it)) {
                is SuspendedResult.Success -> dataState.data.update { res.data }
                is SuspendedResult.Error -> dataState.error.update { res.error }
            }
            dataState.loading.update { false }
        }
    }
    fun getSearchResults() {
        if (results.value.size >= 3) {
            dataState.data.update { list ->
                list.filter { searchResult ->
                    !searchResult.names.none { it.contains(query.value) }
                }
            }
        }
        if (results.value.size < 3) {
            dataState.loading.update { true }
            debouncedSearch(query.value)
        }
    }

}
