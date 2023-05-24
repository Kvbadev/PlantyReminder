package com.example.plantyreminder.data.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantyreminder.domain.Plant

@Database(entities = [Plant::class], version = 1)
abstract class PlantDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}