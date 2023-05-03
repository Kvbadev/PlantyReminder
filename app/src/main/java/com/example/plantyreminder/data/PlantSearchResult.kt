package com.example.plantyreminder.data

data class PlantSearchResult (
    val names: List<String>,
    val imageUrl: String,
    val id: Int = 0,
)