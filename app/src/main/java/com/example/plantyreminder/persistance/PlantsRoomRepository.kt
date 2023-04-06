package com.example.plantyreminder.persistance

import android.content.Context
import com.example.plantyreminder.data.Plant

class PlantsRoomRepository(
    context: Context,
    private val database: PlantDatabase = PlantDatabase.getInstance(context)
) : PlantsRepository {

    override suspend fun getAll(): Result<List<Plant>> {
        val data: List<Plant>
        try {
            data = database.plantDao().getAll()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.success(data)
    }

    override suspend fun insertAll(plants: List<Plant>): Result<Unit> {
        try {
            database.plantDao().insertAll(plants)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.success(Unit)
    }

    override fun insert(plant: Plant) {
//        return database.plantDao().insert(plant)
    }

    override fun delete(plant: Plant) {
//        return database.plantDao().delete(plant)
    }
}