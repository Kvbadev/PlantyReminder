package com.example.plantyreminder.domain

class PlantTimespan(private val minTimespan: Int, private val maxTimespan: Int) {
    fun getTimespan(): String {
        return String.format("%d-%d", minTimespan, maxTimespan)
    }

    fun getEstimatedTimespan() = (minTimespan+maxTimespan)/2L
}