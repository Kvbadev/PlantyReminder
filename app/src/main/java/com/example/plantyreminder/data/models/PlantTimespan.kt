package com.example.plantyreminder.data.models

class PlantTimespan(private val minTimespan: Int, private val maxTimespan: Int) {

    public fun getEstimatedTimespan(): Int {
        return (maxTimespan+minTimespan)/2
    }
}