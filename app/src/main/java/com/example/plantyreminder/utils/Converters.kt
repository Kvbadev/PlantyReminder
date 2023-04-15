package com.example.plantyreminder.utils

import androidx.room.TypeConverter
import com.example.plantyreminder.domain.PlantTimespan
import com.example.plantyreminder.domain.SunPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.time.Duration

class Converters {
    private val gsonConverter = Gson()

    @TypeConverter
    fun objectToString(value: Any?): String = gsonConverter.toJson(value)

    @TypeConverter
    fun plantTimespanToString(value: PlantTimespan) = value.getTimespan()

    @TypeConverter
    fun localDateToString(value: LocalDate): String =
        String.format("%s-%s-%s", value.dayOfMonth, value.monthValue, value.year)

    @TypeConverter
    fun plantTimespanFromString(timespan: String?): PlantTimespan {
        val (min, max) = timespan!!.split('-').map {it.toInt()}
        return PlantTimespan(min, max)
    }

    @TypeConverter
    fun sunPreferenceFromString(preference: String?): List<SunPreference> {
        val listType = object : TypeToken<List<SunPreference>>() {}.type
        return gsonConverter.fromJson(preference, listType)
    }

    @TypeConverter
    fun localDateFromString(date: String?): LocalDate {
        val (day, month, year) = date!!.split("-").map { it.toInt() }
        return LocalDate.of(year, month, day)
    }
}