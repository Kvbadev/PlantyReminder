package com.example.plantyreminder.domain

sealed class SuspendedResult<T> {
    data class Success<T>(val data: T) : SuspendedResult<T>()
    data class Error<T>(val error: ErrorEntity) : SuspendedResult<T>()
}