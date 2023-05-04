package com.example.plantyreminder.utils

import androidx.room.TypeConverter
import com.example.plantyreminder.domain.SunPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

class Converters {
    private val gsonConverter = Gson()

    @TypeConverter
    fun objectToString(value: Any?): String = gsonConverter.toJson(value)

    @TypeConverter
    fun localDateToString(value: LocalDate): String =
        String.format("%s-%s-%s", value.dayOfMonth, value.monthValue, value.year)

    @TypeConverter
    fun sunPreferenceFromString(preference: String?): List<SunPreference> {
        val listType = object : TypeToken<List<SunPreference>>() {}.type
        return gsonConverter.fromJson(preference, listType)
    }

    @TypeConverter
    fun stringToListOfString(strings: String?): List<String> {
        val listType = object : TypeToken<List<SunPreference>>() {}.type
        return gsonConverter.fromJson(strings, listType)
    }

    @TypeConverter
    fun localDateFromString(date: String?): LocalDate {
        val (day, month, year) = date!!.split("-").map { it.toInt() }
        return LocalDate.of(year, month, day)
    }
}