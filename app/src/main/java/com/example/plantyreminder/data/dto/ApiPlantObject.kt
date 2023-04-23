package com.example.plantyreminder.data.dto

import com.google.gson.annotations.SerializedName

data class ApiPlantObject(
    @SerializedName("common_name")
    val commonName: String,

    @SerializedName("other_name")
    val otherNames: List<String>,

    @SerializedName("scientific_name")
    val scientificNames: List<String>,

    @SerializedName("watering")
    val wateringSpan: String,

    @SerializedName("original_url")
    val imageUrl: String?
)