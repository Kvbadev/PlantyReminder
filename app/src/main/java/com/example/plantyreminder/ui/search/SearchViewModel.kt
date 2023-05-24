package com.example.plantyreminder.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.data.SortOption
import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.domain.DataState
import com.example.plantyreminder.domain.SuspendedResult
import com.example.plantyreminder.utils.debounce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiClient: ApiClient,
) : ViewModel() {
    private val dataState: DataState<List<PlantSearchResult>> =
        DataState(data = MutableStateFlow(emptyList()))

    val loadingState = dataState.loading.asStateFlow()
    val results = dataState.data.asStateFlow()
    val errorState = dataState.error.asStateFlow()

    private val sortOption = mutableStateOf(SortOption.NONE)
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

    fun sortResults(newOption: SortOption) {
        sortOption.value = newOption

        if (results == emptyList<PlantSearchResult>()) return
        dataState.data.update {
            when (sortOption.value) {
                SortOption.A_Z -> {
                    it.sortedBy { result ->
                        result.names.first()
                    }
                }
                SortOption.Z_A -> {
                    it.sortedBy { result ->
                        result.names.first()
                    }.reversed()
                }
                else -> {
                    it.sortedBy { result ->
                        result.id
                    }
                }
            }
        }
    }

}
