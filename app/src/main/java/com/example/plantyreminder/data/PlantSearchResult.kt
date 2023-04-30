package com.example.plantyreminder.data

import com.example.plantyreminder.domain.PlantTimespan

data class PlantSearchResult (
    val id: Int,
    val names: List<String>,
    val imageUrl: String
)