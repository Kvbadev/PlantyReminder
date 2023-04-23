package com.example.plantyreminder.data.dto

import com.example.plantyreminder.domain.PlantTimespan

data class PlantSearchResult (
    val names: List<String>,
    val watering: PlantTimespan,
    val imageUrl: String
)