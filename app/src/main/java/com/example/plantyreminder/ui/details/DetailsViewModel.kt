package com.example.plantyreminder.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.domain.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val apiClient: ApiClient,
    private val plantsRepository: PlantsRepository,
) : ViewModel() {
    var plantId: Int? = null
        set(value) {
            if(field == null) field = value
        }

    private val plantDataState: DataState<Plant?> =
        DataState(data = MutableStateFlow(null))
    private val _operationEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()

    val loadingState = plantDataState.loading.asStateFlow()
    val plant = plantDataState.data.asStateFlow()
    val errorState = plantDataState.error.asStateFlow()

    val operationEvent = _operationEvent.asSharedFlow()

    fun getDetailedPlant() {
        plantDataState.loading.update { true }
        viewModelScope.launch {
            when (val res = apiClient.getPlant(plantId!!)) {
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
            is SuspendedResult.Success -> {
                _operationEvent.emit(UiEvent.Success)
            }
            is SuspendedResult.Error -> plantDataState.error.update { res.error }
        }
    }
}