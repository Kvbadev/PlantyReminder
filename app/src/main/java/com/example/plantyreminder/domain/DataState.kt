package com.example.plantyreminder.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DataState<T> (
    val data: MutableStateFlow<T>,
    val error: MutableStateFlow<ErrorEntity?> = MutableStateFlow(null),
    val loading: MutableStateFlow<Boolean> = MutableStateFlow(false),
)