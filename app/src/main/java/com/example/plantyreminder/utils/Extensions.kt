package com.example.plantyreminder.utils

import com.example.plantyreminder.data.dto.ApiPlantObject
import com.example.plantyreminder.data.dto.PlantSearchResult
import com.example.plantyreminder.domain.PlantTimespan

fun ApiPlantObject.toPlantSearchResult() = PlantSearchResult(
    names = listOf(commonName) + otherNames + scientificNames,
    watering = when (wateringSpan) {
        "Average" -> PlantTimespan(1, 3)
        "Frequent" -> PlantTimespan(4, 6)
        "Minimal" -> PlantTimespan(7, 10)
        else -> PlantTimespan(1, 3)
    },
    imageUrl = imageUrl ?: "",
)