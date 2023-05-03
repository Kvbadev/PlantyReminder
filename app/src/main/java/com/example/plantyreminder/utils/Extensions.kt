package com.example.plantyreminder.utils

import com.example.plantyreminder.data.dto.ApiSearchResultObject
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.data.dto.ApiPlantObject
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantWateringSpan
import com.example.plantyreminder.domain.SunPreference

fun ApiSearchResultObject.toPlantSearchResult() = PlantSearchResult(
    id = id,
    names = listOf(commonName) + otherNames + scientificNames,
    imageUrl = imageUrl.url ?: "",
)

fun ApiPlantObject.toPlant() = Plant(
    name = commonName ?: "",
    description = description ?: "",
    waterSpan = PlantWateringSpan.fromText(watering?.lowercase() ?: "AVERAGE"),
    sunlight = sunlight.map {SunPreference.fromText(it)},
    imageUrl = imageUrl.url,
    indoor = indoor,
    family = family,
    origin = "${origin.first()}, ${origin.elementAtOrNull(1) ?: ""}",
    type = type,
    edible = edibleFruit
)