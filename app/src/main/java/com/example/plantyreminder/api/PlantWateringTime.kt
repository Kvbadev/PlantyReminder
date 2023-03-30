package com.example.plantyreminder.api

import com.example.plantyreminder.data.models.PlantTimespan

 enum class PlantWateringTime(val span: PlantTimespan) {
    Frequent(PlantTimespan(1, 3)),
    Average(PlantTimespan(3, 6)),
    Minimal(PlantTimespan(6, 12))
}