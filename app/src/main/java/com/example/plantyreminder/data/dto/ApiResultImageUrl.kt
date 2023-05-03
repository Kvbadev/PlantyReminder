package com.example.plantyreminder.data.dto

import com.google.gson.annotations.SerializedName

data class ApiResultImageUrl(
    @SerializedName("original_url")
    val url: String?
)
