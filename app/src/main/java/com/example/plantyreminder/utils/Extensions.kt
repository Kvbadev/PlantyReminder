package com.example.plantyreminder.utils

import com.example.plantyreminder.api.PlantWateringTime
import com.example.plantyreminder.api.dto.ApiPlantObject
import com.example.plantyreminder.data.models.Plant

fun ApiPlantObject.toPlant() = Plant(
    name = commonName ?: "",
    waterSpan = PlantWateringTime.valueOf(wateringSpan).span,
    temperature = 101,
    imageUrl = imageUrl
)

