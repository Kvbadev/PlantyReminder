package com.example.plantyreminder.data

import androidx.room.TypeConverter
import kotlin.math.max

class PlantTimespan(private val minTimespan: Int, private val maxTimespan: Int) {
    public fun getTimespan(): String {
        return String.format("%d-%d", minTimespan, maxTimespan)
    }


}