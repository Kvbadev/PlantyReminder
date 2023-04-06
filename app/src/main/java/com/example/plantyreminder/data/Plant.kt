package com.example.plantyreminder.data

import androidx.room.*
import com.example.plantyreminder.utils.Converters
import kotlin.time.Duration

@TypeConverters(Converters::class)
@Entity
data class Plant(
    @PrimaryKey val uid: Int,
    val name: String,
    @ColumnInfo("water_span") val waterSpan: PlantTimespan,
    val temperature: Int = 0,
    val sunlight: List<SunPreference> = listOf(SunPreference.UNKNOWN),
    @ColumnInfo("image_url") val imageUrl: String?,
    val indoor: Boolean = false,
    var age: Long = 0,
)
