package com.example.plantyreminder.api.dto

import com.google.gson.annotations.SerializedName

data class ApiPlantObjectList (
    @SerializedName("data")
    var results: List<ApiPlantObject>
)