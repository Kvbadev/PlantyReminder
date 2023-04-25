package com.example.plantyreminder.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException
import kotlin.math.log

fun <T> debounce(
    waitMs: Long = 300L,
    scope: CoroutineScope,
    destinationFunction: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null;

    return { param: T ->
        try {
            debounceJob?.cancel()
        } catch (_: CancellationException) {
        }
        finally {
            debounceJob = scope.launch {
                delay(waitMs)
                destinationFunction(param)
            }
        }
    }
}