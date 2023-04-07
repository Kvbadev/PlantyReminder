package com.example.plantyreminder.data.dto

import com.google.gson.annotations.SerializedName

//@JvmInline
//value class ApiPlantObjectList(@SerializedName("data") val plants: List<Plant>)
data class ApiPlantObjectList (
    @SerializedName("data")
    var results: List<ApiPlantObject>
)