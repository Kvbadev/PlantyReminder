package com.example.plantyreminder.data

import kotlin.math.absoluteValue

enum class WateringState {
    SCHEDULED, TODAY, OVERDUE;

    companion object {
        fun fromTime(daysToWatering: Long): WateringState {
            return when {
                daysToWatering > 0 -> SCHEDULED
                daysToWatering == 0L -> TODAY
                else -> OVERDUE
            }
        }
    }
}