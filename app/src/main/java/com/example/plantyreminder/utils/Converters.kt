package com.example.plantyreminder.utils

import androidx.room.TypeConverter
import com.example.plantyreminder.data.PlantTimespan
import com.example.plantyreminder.data.SunPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.time.Duration

class Converters {
    private val gsonConverter = Gson()
    @TypeConverter
    fun objectToJson(value: Any?): String = gsonConverter.toJson(value)
    @TypeConverter
    fun plantTimespanFromString(timespan: String): PlantTimespan {
        return gsonConverter.fromJson(timespan, PlantTimespan::class.java)
    }
    @TypeConverter
    fun sunPreferenceFromString(preference: String?): List<SunPreference> {
        val listType = object : TypeToken<List<SunPreference>>() {}.type
        return gsonConverter.fromJson(preference, listType)
    }
    @TypeConverter
    fun durationFromString(string: String?): Duration {
        return gsonConverter.fromJson(string, Duration::class.java)
    }
}