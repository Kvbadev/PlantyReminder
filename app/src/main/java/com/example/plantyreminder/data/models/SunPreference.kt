package com.example.plantyreminder.data.models

enum class SunPreference(val pref: String) {
    FULL_SUN("full sun"),
    PART_SHADE("part shade"),
    FULL_SHADE("full shade"),
    DEFAULT("");

    companion object {
        fun fromText(text: String): SunPreference {
            return values().firstOrNull() {
                it.pref == text
            } ?: DEFAULT
        }
    }
}