package com.example.plantyreminder.domain

interface PlantsRepository {
    suspend fun getAll(): SuspendedResult<List<Plant>>;
    suspend fun getPlant(uid: Long): SuspendedResult<Plant?>
    suspend fun insertAll(plants: List<Plant>): SuspendedResult<Unit>
    suspend fun insert(plant: Plant): SuspendedResult<Long>
    suspend fun delete(plant: Plant): SuspendedResult<Unit>
    suspend fun deleteAll(): SuspendedResult<Unit>
}