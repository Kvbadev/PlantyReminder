package com.example.plantyreminder.api.dto

import com.google.gson.annotations.SerializedName

data class ApiPlantObject(
    @SerializedName("id")
    val id: Int,

    @SerializedName("common_name")
    val commonName: String,

    @SerializedName("watering")
    val wateringSpan: String,

    @SerializedName("sunlight")
    val sunlight_preference: List<String>,

    @SerializedName("original_url")
    val imageUrl: String

)