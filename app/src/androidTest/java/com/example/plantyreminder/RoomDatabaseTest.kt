package com.example.plantyreminder

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.plantyreminder.data.persistance.PlantDao
import com.example.plantyreminder.data.persistance.PlantDatabase
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantTimespan
import com.example.plantyreminder.domain.SunPreference
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class RoomDatabaseTest {

    private lateinit var database: PlantDatabase
    private lateinit var dao: PlantDao

    @Before
    fun initialize() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PlantDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.plantDao()
    }

    @After
    fun closeDatabase() = database.close()

    @Test
    fun testInsertPlant() = runBlocking {
        val plant =
            Plant(11, "Test plant", PlantTimespan(1, 3), 22, listOf(SunPreference.FULL_SUN), "url")
        assertNull(dao.getPlant(plant.uid))

        val insertedPlantId = dao.insert(plant)
        assertEquals(plant.uid, insertedPlantId)

        dao.getPlant(plant.uid)?.let {
            assertEquals(it.uid, plant.uid)
            assertEquals(it.name, plant.name)
        } ?: fail("Player not found")
    }
}