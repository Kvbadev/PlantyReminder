package com.example.plantyreminder.data.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantyreminder.domain.Plant

@Database(entities = [Plant::class], version = 1)
abstract class PlantDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {
        private var instance: PlantDatabase? = null

        fun getInstance(context: Context): PlantDatabase {
            if(instance == null) instance = Room.databaseBuilder(context, PlantDatabase::class.java, "plants").build()

            return instance!!
        }
    }
}