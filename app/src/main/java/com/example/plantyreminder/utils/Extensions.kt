package com.example.plantyreminder.utils

import com.example.plantyreminder.api.PlantWateringTime
import com.example.plantyreminder.api.dto.ApiPlantObject
import com.example.plantyreminder.data.Plant
import com.example.plantyreminder.data.SunPreference
import java.util.*

fun ApiPlantObject.toPlant() = Plant(
    uid = 3,
    name = commonName,
    waterSpan = PlantWateringTime.valueOf(wateringSpan).span,
    temperature = 101,
    sunlight = if(sunlight_preference.isNotEmpty()) sunlight_preference.map {
      SunPreference.fromText(it)
    } else listOf(SunPreference.UNKNOWN),
    imageUrl = imageUrl,
    indoor = false,
    age = 0
)

fun Long.getHours() = this / 3600
fun Long.getDays() = getHours() / 24

