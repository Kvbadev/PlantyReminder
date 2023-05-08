package com.example.plantyreminder.domain

import androidx.room.*
import com.example.plantyreminder.utils.Converters
import java.time.LocalDate

@TypeConverters(Converters::class)
@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("water_span") val waterSpan: PlantWateringSpan? = null,
    @ColumnInfo("sunlight") val sunlight: List<SunPreference>? = null,
    @ColumnInfo("image_url") val imageUrl: String? = null,
    @ColumnInfo("indoor") val indoor: Boolean? = null,
    @ColumnInfo("created_at") val createdAt: LocalDate = LocalDate.now(),
    @ColumnInfo("next_watering") val nextWatering: LocalDate = LocalDate.now(),
    @ColumnInfo("family") val family: String? = null,
    @ColumnInfo("origin") val origin: List<String>? = null,
    @ColumnInfo("type") val type: String? = null,
    @ColumnInfo("edible") val edible: Boolean? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Plant

        if (uid != other.uid) return false
        if (name != other.name) return false
        if (waterSpan != other.waterSpan) return false
        if (imageUrl != other.imageUrl) return false
        if (indoor != other.indoor) return false
        if (createdAt != other.createdAt) return false
        if (nextWatering != other.nextWatering) return false
        if (description != other.description) return false
        if (family != other.family) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + waterSpan.hashCode()
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + indoor.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + nextWatering.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + family.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

}
