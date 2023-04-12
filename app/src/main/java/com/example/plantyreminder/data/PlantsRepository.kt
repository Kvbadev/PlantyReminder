package com.example.plantyreminder.data

import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.SuspendedResult

interface PlantsRepository {
    suspend fun getAll(): SuspendedResult<List<Plant>>;
    suspend fun insertAll(plants: List<Plant>): SuspendedResult<Unit>
    suspend fun insert(plant: Plant): SuspendedResult<Long>
    fun delete(plant: Plant)
    suspend fun deleteAll(): SuspendedResult<Unit>
}