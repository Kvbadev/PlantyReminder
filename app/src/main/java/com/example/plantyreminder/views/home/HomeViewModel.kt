package com.example.plantyreminder.views.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.R
import com.example.plantyreminder.data.PlantsRepository
import com.example.plantyreminder.domain.ErrorEntity
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.SuspendedResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean

class HomeViewModel(
    private val repository: PlantsRepository,
) : ViewModel() {
    private val _errorState: MutableStateFlow<ErrorEntity?> = MutableStateFlow(null)
    val errorState = _errorState.asStateFlow()

    fun getUserPlants(): List<Plant> {
        val plants: SnapshotStateList<Plant> = SnapshotStateList()

        viewModelScope.launch {
            when (val res = repository.getAll()) {
                is SuspendedResult.Success -> {
                    plants.clear()
                    plants.addAll(res.data)
                }
                is SuspendedResult.Error -> {
                    _errorState.value = res.error
                }
            }
        }
        return plants
    }

    fun removeAllPlants() = viewModelScope.launch {
        when(val res = repository.deleteAll()) {
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