package com.example.plantyreminder.utils

import com.example.plantyreminder.data.dto.ApiResultImageUrl
import com.example.plantyreminder.data.dto.ApiSearchResultObject
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ApiSearchResultDeserializer : JsonDeserializer<ApiSearchResultObject> {
    override fun deserialize(
        json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?
    ): ApiSearchResultObject? {
        if (json == null) throw Exception("JSON object was null")
        val isPaywall = json.toString().contains("https://perenual.com/subscription-api-pricing")

        return if (!isPaywall) Gson().fromJson(
            json.asJsonObject, ApiSearchResultObject::class.java
        ) else ApiSearchResultObject(
            json.asJsonObject["id"].asInt,
            json.asJsonObject["common_name"].asString,
            null,
            null,
            null
        )
    }
}