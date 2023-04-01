package com.example.plantyreminder.views.home

import androidx.lifecycle.ViewModel
import com.example.plantyreminder.data.models.Plant

class HomeViewModel : ViewModel() {

    fun getUserPlants(): List<Plant> {
        return SampleData.plantsSample;
    }
}