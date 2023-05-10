package com.example.plantyreminder.utils

import com.example.plantyreminder.data.dto.ApiResultImageUrl
import com.example.plantyreminder.data.dto.ApiSearchResultObject
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ApiSearchResultDeserializer : JsonDeserializer<ApiSearchResultObject> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ApiSearchResultObject? {
        val wrapperGson = Gson()
        var image: ApiResultImageUrl?
        if(json == null || context == null) throw Exception("json or context was null")
        try {
            image = context.deserialize<ApiResultImageUrl>(
                json.asJsonObject["default_image"],
                ApiResultImageUrl::class.java
            )
        } catch (e: com.google.gson.JsonSyntaxException) {
            if(e.message.toString().contains("Expected BEGIN_OBJECT but was STRING")) {
                return ApiSearchResultObject(
                    json.asJsonObject["id"].asInt,
                    json.asJsonObject["common_name"].asString,
                    null, null, null
                )
            }
            throw e
        }
        if(image?.url != null) return wrapperGson.fromJson(json, typeOfT)

        return null
    }
}