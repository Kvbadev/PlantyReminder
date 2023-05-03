package com.example.plantyreminder.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.domain.DataState
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantsRepository
import com.example.plantyreminder.domain.SuspendedResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.inject

class DetailsViewModel(
    private val apiClient: ApiClient
    ) : ViewModel() {

        private val dataState: DataState<Plant?> =
            DataState(data = MutableStateFlow(null))

        val loadingState = dataState.loading.asStateFlow()
        val plant = dataState.data.asStateFlow()
        val errorState = dataState.error.asStateFlow()

    fun getDetailedPlant(id: Int) {
        dataState.loading.update { true }
        viewModelScope.launch {
            when (val res = apiClient.getPlant(id)) {
                is SuspendedResult.Success -> dataState.data.update { res.data }
                is SuspendedResult.Error -> dataState.error.update { res.error }
            }
            dataState.loading.update { false }
        }
    }
}