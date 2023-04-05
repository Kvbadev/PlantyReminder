package com.example.plantyreminder.data

import androidx.room.TypeConverter
import kotlin.math.max

class PlantTimespan(internal val minTimespan: Int, internal val maxTimespan: Int) {
    public fun getEstimatedTimespan(): Int {
        return (maxTimespan + minTimespan) / 2
    }


}