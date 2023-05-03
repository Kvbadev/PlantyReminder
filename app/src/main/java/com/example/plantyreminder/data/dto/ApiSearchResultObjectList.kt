package com.example.plantyreminder.data.dto

import com.google.gson.annotations.SerializedName

data class ApiSearchResultObjectList (
    @SerializedName("data")
    var results: List<ApiSearchResultObject>
)