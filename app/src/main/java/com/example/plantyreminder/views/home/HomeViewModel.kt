package com.example.plantyreminder.views.home

import androidx.lifecycle.ViewModel
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.data.Plant
import com.example.plantyreminder.persistance.PlantsRepository
import com.example.plantyreminder.persistance.PlantsRoomRepository

class HomeViewModel(
    val repository: PlantsRepository = MainActivity.REPOSITORY_DEPENDENCY
) : ViewModel() {
    fun getUserPlants(): List<Plant> {
//        return repository.getAll()
        return SampleData.plantsSample;
    }
}