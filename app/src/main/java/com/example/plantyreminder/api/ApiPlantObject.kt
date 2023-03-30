package com.example.plantyreminder.api

import com.google.gson.annotations.SerializedName

class ApiPlantObject {
    @SerializedName("id")
    val id: Int? = null;

    @SerializedName("common_name")
    val commonName: String = ""

    @SerializedName("watering")
    val wateringSpan: String = "";

    @SerializedName("default_image:original_url")
    val imageUrl: String = "";
}