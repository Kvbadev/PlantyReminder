package com.example.plantyreminder.domain

enum class PlantWateringSpan(val value: String) {
    FREQUENT("frequent"),
    AVERAGE("average"),
    MINIMUM("minimum"),
    NONE("none");
    companion object {
        fun fromText(text: String): PlantWateringSpan {
            return values().firstOrNull() {
                it.value == text
            } ?: AVERAGE
        }
    }

    fun getEstimatedTimespan(): Long = when(this) {
        FREQUENT -> 3L
        AVERAGE -> 5L
        MINIMUM -> 8L
        NONE -> 0L
    }
}