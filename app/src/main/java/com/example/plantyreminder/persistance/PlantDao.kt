package com.example.plantyreminder.persistance

import androidx.room.*
import com.example.plantyreminder.data.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant")
    fun getAll(): List<Plant>

    @Insert
    fun insert(plant: Plant)

    @Insert
    fun insertAll(plants: List<Plant>)

    @Delete
    fun delete(plant: Plant)
}