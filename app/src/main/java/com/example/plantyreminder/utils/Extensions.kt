package com.example.plantyreminder.utils

import com.example.plantyreminder.api.PlantWateringTime
import com.example.plantyreminder.api.dto.ApiPlantObject
import com.example.plantyreminder.data.models.Plant
import com.example.plantyreminder.data.models.SunPreference
import kotlin.time.Duration

fun ApiPlantObject.toPlant() = Plant(
    name = commonName,
    waterSpan = PlantWateringTime.valueOf(wateringSpan).span,
    temperature = 101,
    sunlight = if(sunlight_preference.isNotEmpty()) sunlight_preference.map {
      SunPreference.fromText(it)
    } else listOf(SunPreference.UNKNOWN),
    imageUrl = imageUrl,
    indoor = false,
    age = Duration.ZERO
)

