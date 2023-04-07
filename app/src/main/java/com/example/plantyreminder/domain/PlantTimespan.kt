package com.example.plantyreminder.domain

class PlantTimespan(private val minTimespan: Int, private val maxTimespan: Int) {
    public fun getTimespan(): String {
        return String.format("%d-%d", minTimespan, maxTimespan)
    }


}