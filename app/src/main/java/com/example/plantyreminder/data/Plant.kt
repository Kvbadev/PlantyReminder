package com.example.plantyreminder.data

import androidx.room.*
import com.example.plantyreminder.persistance.Converters
import kotlin.time.Duration

@Entity
data class Plant(
    val name: String,
    @ColumnInfo("water_span") val waterSpan: PlantTimespan,
    val temperature: Int = 0,
    val sunlight: List<SunPreference> = listOf(SunPreference.UNKNOWN),
    @ColumnInfo("image_url") val imageUrl: String?,
    val indoor: Boolean = false,
    val age: Int = 0,
    @PrimaryKey val uid: Int = 0,
)
