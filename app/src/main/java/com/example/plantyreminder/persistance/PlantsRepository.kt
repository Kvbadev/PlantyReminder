package com.example.plantyreminder.persistance

import android.content.Context
import androidx.room.Room
import com.example.plantyreminder.data.Plant

interface PlantsRepository {
    suspend fun getAll(): Result<List<Plant>>;
    suspend fun insertAll(plants: List<Plant>): Result<Unit>
    fun insert(plant: Plant)
    fun delete(plant: Plant)
}