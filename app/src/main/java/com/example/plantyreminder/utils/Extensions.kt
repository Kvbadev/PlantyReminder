package com.example.plantyreminder.utils

import com.example.plantyreminder.data.dto.ApiPlantObject
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantTimespan
import com.example.plantyreminder.domain.SunPreference

fun ApiPlantObject.toPlant() = Plant(
    uid = 3,
    name = commonName,
    waterSpan = when (wateringSpan) {
        "Frequent" -> PlantTimespan(1, 3)
        "Average" -> PlantTimespan(3, 6)
        "Minimal" -> PlantTimespan(6, 12)
        else -> throw UnsupportedOperationException("Unsupported watering span")
    },
    temperature = 101,
    sunlight = if (sunlight_preference.isNotEmpty()) sunlight_preference.map {
        SunPreference.fromText(it)
    } else listOf(SunPreference.UNKNOWN),
    imageUrl = imageUrl,
    indoor = false,
)