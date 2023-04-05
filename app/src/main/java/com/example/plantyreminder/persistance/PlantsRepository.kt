package com.example.plantyreminder.persistance

import android.content.Context
import androidx.room.Room
import com.example.plantyreminder.data.Plant

interface PlantsRepository {
    fun getAll(): List<Plant>;
    fun insertAll(plants: List<Plant>)
    fun insert(plant: Plant)
    fun delete(plant: Plant)
    fun initialize(context: Context)
}