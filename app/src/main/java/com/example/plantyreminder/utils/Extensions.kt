package com.example.plantyreminder.utils

import com.example.plantyreminder.data.dto.ApiSearchResultObject
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.data.dto.ApiPlantObject
import com.example.plantyreminder.data.dto.ApiResultImageUrl
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantWateringSpan
import com.example.plantyreminder.domain.SunPreference
import com.google.gson.Gson
import com.google.gson.internal.LinkedHashTreeMap
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import kotlin.reflect.full.declaredMemberProperties

fun ApiSearchResultObject.toPlantSearchResult() = PlantSearchResult(
    id = id,
    names = listOf(commonName) + (otherNames ?: emptyList()) + (scientificNames ?: emptyList()),
    imageUrl = imageUrl?.url ?: "",
)

fun ApiPlantObject.toPlant() = Plant(
    name = commonName ?: "",
    description = description?.trim { it == '\n' } ?: "",
    waterSpan = PlantWateringSpan.fromText(watering?.lowercase() ?: "AVERAGE"),
    sunlight = sunlight.map { SunPreference.fromText(it) },
    imageUrl = imageUrl.url,
    indoor = indoor,
    family = family,
    origin = origin,
    type = type,
    edible = edibleFruit
)