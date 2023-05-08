package com.example.plantyreminder.domain

sealed class UiEvent {
    data class Loading(val id: Long = 0L) : UiEvent()
    data class Success(val id: Long = 0L) : UiEvent()
}