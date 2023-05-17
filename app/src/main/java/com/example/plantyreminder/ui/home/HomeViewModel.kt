package com.example.plantyreminder.ui.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantyreminder.data.notifications.AppNotification
import com.example.plantyreminder.domain.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class HomeViewModel(
    private val repository: PlantsRepository,
) : ViewModel() {

    private val dataState: DataState<List<Plant>> =
        DataState(data = MutableStateFlow(emptyList()))
    private val _updatingEvent: MutableSharedFlow<UiEvent> = MutableSharedFlow()

    var loadingState = dataState.loading.asStateFlow()
    val results = dataState.data.asStateFlow()
    val errorState = dataState.error.asStateFlow()

    val updatingEvent = _updatingEvent.asSharedFlow()

    internal fun updateUserPlants() {
        dataState.loading.update { true }
        viewModelScope.launch {
            when (val res = repository.getAll()) {
                is SuspendedResult.Success -> {
                    dataState.data.update { res.data }
                }
                is SuspendedResult.Error -> {
                    dataState.error.update { res.error }
                }
            }
            dataState.loading.update { false }
        }
    }

    fun removeAllPlants() = viewModelScope.launch {
        when (val res = repository.deleteAll()) {
            is SuspendedResult.Success -> {
                println("plant table cleared!")
            }
            is SuspendedResult.Error -> {
                throw Exception(res.error.message)
            }
        }
    }


    fun addUserPlants(plants: List<Plant>) = viewModelScope.launch {
        when (val res = repository.insertAll(plants)) {
            is SuspendedResult.Success -> {
                println("Supplied data inserted!")
            }
            is SuspendedResult.Error -> {
                throw Exception(res.error.message)
            }
        }
    }

    fun updatePlant(plant: Plant, context: Context) = viewModelScope.launch {
        _updatingEvent.emit(UiEvent.Loading)
        when (val res = repository.update(plant)) {
            is SuspendedResult.Success -> {
                val nextWatering = SystemClock.elapsedRealtime() + (ChronoUnit.DAYS.between(
                    LocalDate.now(),
                    plant.nextWatering
                ) * 24 * 3600 * 1000)
                scheduleNotification(plant.name, nextWatering, context)
                _updatingEvent.emit(UiEvent.Success)
            }
            is SuspendedResult.Error -> {
                dataState.error.update { res.error }
            }
        }
    }

    private fun scheduleNotification(plantName: String, delay: Long, context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AppNotification::class.java)
        intent.putExtra("content", "Your $plantName need some water!")
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            delay,
            pendingIntent
        )
    }
}