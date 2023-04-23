package com.example.plantyreminder.ui.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.domain.PlantsRepository
import com.example.plantyreminder.domain.ErrorEntity
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.SuspendedResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PlantsRepository,
) : ViewModel() {

    private val _errorState: MutableStateFlow<ErrorEntity?> = MutableStateFlow(null)
    val errorState = _errorState.asStateFlow()

    private val _loadingPlantsState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingPlantsState = _loadingPlantsState.asStateFlow()

    private val _plants: MutableStateFlow<List<Plant>> = MutableStateFlow(emptyList());
    val plants = _plants.asStateFlow()

    internal fun updateUserPlants() {
        _loadingPlantsState.value = true
        viewModelScope.launch {
            when (val res = repository.getAll()) {
                is SuspendedResult.Success -> {
                    _plants.update { res.data }
                }
                is SuspendedResult.Error -> {
                    _errorState.value = res.error
                }
            }
            _loadingPlantsState.value = false
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
}