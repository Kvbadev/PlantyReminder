package com.example.plantyreminder.data.models

data class Plant(
    val name: String,
    val waterSpan: PlantTimespan,
    val temperature: Int = 0,
    val sunlight: List<SunPreference> = listOf(SunPreference.DEFAULT),
    val imageUrl: String?,
)
