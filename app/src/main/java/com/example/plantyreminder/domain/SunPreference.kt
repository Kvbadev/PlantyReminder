package com.example.plantyreminder.domain

enum class SunPreference(val pref: String, val description: String) {
    FULL_SUN("full sun", "The plant should be in the sun for at least six hours per day"),
    PART_SHADE("part shade", "The plant needs the sun for two hours each day or shade for at least half the day"),
    FULL_SHADE("full shade", "The plant take in less than an hour of direct sunlight each day"),
    UNKNOWN("","Unknown");

    companion object {
        fun fromText(text: String): SunPreference {
            return values().firstOrNull() {
                it.pref == text.lowercase()
            } ?: UNKNOWN
        }
    }
}