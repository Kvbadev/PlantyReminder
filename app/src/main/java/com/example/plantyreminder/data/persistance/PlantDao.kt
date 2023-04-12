package com.example.plantyreminder.data.persistance

import androidx.room.*
import com.example.plantyreminder.domain.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant")
    suspend fun getAll(): List<Plant>

    @Query("SELECT * FROM plant WHERE uid IS :uid")
    suspend fun getPlant(uid: Long): Plant?

    @Insert
    suspend fun insert(plant: Plant): Long

    @Insert
    suspend fun insertAll(plants: List<Plant>)

    @Delete
    suspend fun delete(plant: Plant)

    @Query("DELETE FROM plant")
    suspend fun deleteAll()

}