package com.example.plantyreminder.persistance

import androidx.room.*
import com.example.plantyreminder.data.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant")
    suspend fun getAll(): List<Plant>

    @Insert
    suspend fun insert(plant: Plant)

    @Insert
    suspend fun insertAll(plants: List<Plant>)

    @Delete
    suspend fun delete(plant: Plant)
}