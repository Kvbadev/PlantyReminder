package com.example.plantyreminder.data.dto

import com.google.gson.annotations.SerializedName

data class ApiPlantObject(
    @SerializedName("id")
    val id: Int,

    @SerializedName("common_name")
    val commonName: String,

    @SerializedName("other_name")
    val otherNames: List<String>,

    @SerializedName("scientific_name")
    val scientificNames: List<String>,

    @SerializedName("default_image")
    val imageUrl: PlantImageUrl
)

data class PlantImageUrl(
    @SerializedName("original_url")
    val url: String?
)