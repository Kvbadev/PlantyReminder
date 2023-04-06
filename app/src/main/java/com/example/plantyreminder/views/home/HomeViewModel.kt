package com.example.plantyreminder.views.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.data.Plant
import com.example.plantyreminder.persistance.PlantsRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PlantsRepository,

) : ViewModel() {
    fun getUserPlants(): List<Plant> {
        val userPlants = SnapshotStateList<Plant>()

        viewModelScope.launch {
            repository.getAll().onSuccess {
                userPlants.clear()
                userPlants.addAll(it)
            }
        }

        return userPlants
    }
    fun addUserPlants() {
        viewModelScope.launch {
            repository.insertAll(SampleData.plantsSample).onSuccess {
                println("Data inserted!")
            }.onFailure {
                println(it.message)
            }
        }
    }
}