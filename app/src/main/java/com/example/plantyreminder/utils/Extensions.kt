package com.example.plantyreminder.utils

import com.example.plantyreminder.data.dto.ApiPlantObject
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.domain.PlantTimespan

fun ApiPlantObject.toPlantSearchResult() = PlantSearchResult(
    id = id,
    names = listOf(commonName) + otherNames + scientificNames,
    imageUrl = imageUrl.url ?: "",
)