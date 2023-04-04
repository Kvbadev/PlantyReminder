package com.example.plantyreminder.data.models

import kotlin.time.Duration

data class Plant(
    val name: String,
    val waterSpan: PlantTimespan,
    val temperature: Int = 0,
    val sunlight: List<SunPreference> = listOf(SunPreference.UNKNOWN),
    val imageUrl: String?,
    val indoor: Boolean = false,
    val age: Duration = Duration.ZERO
)
