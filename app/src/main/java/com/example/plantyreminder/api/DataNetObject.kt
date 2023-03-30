package com.example.plantyreminder.api

import com.google.gson.annotations.SerializedName

class DataNetObject {

    @SerializedName("data")
    lateinit var data: List<ApiPlantObject>
}