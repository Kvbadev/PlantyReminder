package com.example.plantyreminder.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.domain.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val apiClient: ApiClient,
    private val plantsRepository: PlantsRepository
) : ViewModel() {

    private val plantDataState: DataState<Plant?> =
        DataState(data = MutableStateFlow(null))
    private val _operationEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()

    val loadingState = plantDataState.loading.asStateFlow()
    val plant = plantDataState.data.asStateFlow()
    val errorState = plantDataState.error.asStateFlow()

    val operationEvent = _operationEvent.asSharedFlow()

    fun getDetailedPlant(id: Int) {
        plantDataState.loading.update { true }
        viewModelScope.launch {
            when (val res = apiClient.getPlant(id)) {
                is SuspendedResult.Success -> plantDataState.data.update { res.data }
                is SuspendedResult.Error -> {
                    if (res.error != ErrorEntity.Network.ApiPaywall)
                        plantDataState.error.update {
                            res.error
                        }
                }
            }
            plantDataState.loading.update { false }
        }
    }

    fun addPlantToLibrary(plant: Plant) = viewModelScope.launch {
        _operationEvent.emit(UiEvent.Loading)
        delay(2000L)
        when (val res = plantsRepository.insert(plant)) {
            is SuspendedResult.Success -> _operationEvent.emit(UiEvent.Success)
            is SuspendedResult.Error -> plantDataState.error.update { res.error }
        }
    }
}