package com.example.plantyreminder.domain

import androidx.room.*
import com.example.plantyreminder.utils.Converters
import java.time.LocalDate

@TypeConverters(Converters::class)
@Entity
data class Plant(
    @PrimaryKey val uid: Long,
    val name: String,
    @ColumnInfo("water_span") val waterSpan: PlantTimespan,
    val temperature: Int = 0,
    val sunlight: List<SunPreference> = listOf(SunPreference.UNKNOWN),
    @ColumnInfo("image_url") val imageUrl: String?,
    val indoor: Boolean = false,
    @ColumnInfo("created_at") val createdAt: LocalDate = LocalDate.now(),
    @ColumnInfo("next_watering") val nextWatering: LocalDate = LocalDate.now().plusDays(waterSpan.getEstimatedTimespan())
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Plant

        if (uid != other.uid) return false
        if (name != other.name) return false
        if (temperature != other.temperature) return false
        if (sunlight != other.sunlight) return false
        if (imageUrl != other.imageUrl) return false
        if (indoor != other.indoor) return false
        if (createdAt != other.createdAt) return false
        if (nextWatering != other.nextWatering) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + temperature
        result = 31 * result + sunlight.hashCode()
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + indoor.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + nextWatering.hashCode()
        return result
    }
}
