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

fun ApiSearchResultObject.toPlantSearchResult(): PlantSearchResult? {
    //Always true if the desired plant is behind the paywall
    if (this.imageUrl.toString().contains("https://perenual.com/subscription-api-pricing")) return null

    val otherNames = otherNames as? List<*>
    val scientificNames = scientificNames as? List<*>
    val image = imageUrl as LinkedTreeMap<*, *>
    if(otherNames == null || scientificNames == null) return null

    val allNames = (listOf(commonName) + otherNames + scientificNames) as List<*>

    return PlantSearchResult(
        id = id,
        names = allNames as? List<String> ?: emptyList(),
        imageUrl = image["original_url"] as? String ?: "",
    )
}

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