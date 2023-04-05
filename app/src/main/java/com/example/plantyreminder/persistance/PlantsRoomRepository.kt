package com.example.plantyreminder.persistance

import android.content.Context
import com.example.plantyreminder.data.Plant

class PlantsRoomRepository(

) : PlantsRepository {
    private lateinit var database: PlantDatabase;

    override fun initialize(context: Context) {
        database = PlantDatabase.getInstance(context)
    }

    override fun getAll(): List<Plant> {
        return database.plantDao().getAll()
    }

    override fun insertAll(plants: List<Plant>) {
        return database.plantDao().insertAll(plants)
    }

    override fun insert(plant: Plant) {
        return database.plantDao().insert(plant)
    }

    override fun delete(plant: Plant) {
        return database.plantDao().delete(plant)
    }
}