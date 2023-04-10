package com.example.plantyreminder.domain

import androidx.compose.ui.platform.LocalDensity
import androidx.room.*
import com.example.plantyreminder.utils.Converters
import java.time.LocalDate
import java.util.concurrent.TimeUnit

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
    @ColumnInfo("created_at") val createdAt: LocalDate = LocalDate.now(),
    @ColumnInfo("next_watering") val nextWatering: LocalDate = LocalDate.now().plusDays(waterSpan.getEstimatedTimespan())
)
