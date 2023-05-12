package com.example.plantyreminder.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.domain.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PlantsRepository,
) : ViewModel() {

    private val dataState: DataState<List<Plant>> =
        DataState(data = MutableStateFlow(emptyList()))
    private val _updatingEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()

    var loadingState = dataState.loading.asStateFlow()
    val results = dataState.data.asStateFlow()
    val errorState = dataState.error.asStateFlow()

    val updatingEvent = _updatingEvent.asSharedFlow()

    internal fun updateUserPlants() {
        dataState.loading.update { true }
        viewModelScope.launch {
            when (val res = repository.getAll()) {
                is SuspendedResult.Success -> {
                    dataState.data.update { res.data }
                }
                is SuspendedResult.Error -> {
                    dataState.error.update { res.error }
                }
            }
            dataState.loading.update { false }
        }
    }

    fun removeAllPlants() = viewModelScope.launch {
        when (val res = repository.deleteAll()) {
            is SuspendedResult.Success -> {
                println("plant table cleared!")
            }
            is SuspendedResult.Error -> {
                throw Exception(res.error.message)
            }
        }
    }


    fun addUserPlants(plants: List<Plant>) = viewModelScope.launch {
        when (val res = repository.insertAll(plants)) {
            is SuspendedResult.Success -> {
                println("Supplied data inserted!")
            }
            is SuspendedResult.Error -> {
                throw Exception(res.error.message)
            }
        }
    }

    fun updatePlant(plant: Plant) = viewModelScope.launch {
        _updatingEvent.emit(UiEvent.Loading)
        when (val res = repository.update(plant)) {
            is SuspendedResult.Success -> {
                _updatingEvent.emit(UiEvent.Success)
            }
            is SuspendedResult.Error -> {
                dataState.error.update { res.error }
            }
        }
    }
}