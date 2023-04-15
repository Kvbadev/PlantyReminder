package com.example.plantyreminder

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.plantyreminder.domain.PlantsRepository
import com.example.plantyreminder.data.persistance.PlantDatabase
import com.example.plantyreminder.data.persistance.PlantsRoomRepository
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantTimespan
import com.example.plantyreminder.domain.SuspendedResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)

class PlantsRoomRepositoryTest {

    companion object {
        val standalonePlant = Plant(12, "Test Plant", PlantTimespan(1, 3), 22, imageUrl = "")
        val plantsList = listOf(
            Plant(1, "plant1", PlantTimespan(1, 3), 22, imageUrl = ""),
            Plant(2, "plant2", PlantTimespan(2, 4), 24, imageUrl = ""),
            Plant(3, "plant3", PlantTimespan(3, 5), 26, imageUrl = "")
        )
    }

    private lateinit var db: PlantDatabase
    private lateinit var repository: PlantsRepository

    @Before
    fun initialize() {

        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PlantDatabase::class.java
        ).allowMainThreadQueries().build()

        repository = PlantsRoomRepository(
            ApplicationProvider.getApplicationContext(),
            db
        )
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun testGetPlant() = runTest {

        var plant = repository.getPlant(standalonePlant.uid) as SuspendedResult.Success
        Assert.assertNull("Received value instead of null", plant.data)

        db.plantDao().insert(standalonePlant)
        plant = repository.getPlant(standalonePlant.uid) as SuspendedResult.Success

        Assert.assertEquals(
            "Received plant was not the same as the one inserted before",
            standalonePlant,
            plant.data
        )

    }

    @Test
    fun testInsertPlant() = runTest {

        val insertedPlantId = repository.insert(standalonePlant) as SuspendedResult.Success
        Assert.assertEquals(standalonePlant.uid, insertedPlantId.data)

        val res = db.plantDao().getPlant(standalonePlant.uid)
        Assert.assertNotNull("Plant's not been found", res)
        Assert.assertEquals("Plants' IDs were not the same", standalonePlant.uid, res!!.uid)
        Assert.assertEquals("Plants' names were not the same", standalonePlant.name, res.name)
    }

    @Test
    fun testDeletePlant() = runTest {
        repository.insert(standalonePlant) as SuspendedResult.Success

        when (repository.delete(standalonePlant)) {
            is SuspendedResult.Success -> {
                Assert.assertNull(
                    "The plant with id: ${standalonePlant.uid}'s not been deleted",
                    (repository.getPlant(standalonePlant.uid) as SuspendedResult.Success).data
                )
            }
            else -> Assert.fail("Operation was not successful")
        }
    }

    @Test
    fun testInsertAllPlants() = runTest {
        when (repository.insertAll(plantsList)) {
            is SuspendedResult.Success -> {
                val plants = db.plantDao().getAll()
                Assert.assertEquals(
                    "Size of the list was ${plants.size} instead of ${plantsList.size} ",
                    plantsList.size, plants.size
                )
            }
            else -> Assert.fail("Operation was not successful")
        }
    }

    @Test
    fun testGetAllPlants() = runTest {
        db.plantDao().insertAll(plantsList)

        when (val plants = repository.getAll()) {
            is SuspendedResult.Success -> {
                Assert.assertEquals(
                    "Received list size was ${plants.data.size} instead of ${plantsList.size}",
                    plantsList.size, plants.data.size
                )
            }
            else -> Assert.fail("Operation was not successful")
        }
    }

    @Test
    fun testDeleteAllPlants() = runTest {
        db.plantDao().insertAll(plantsList)

        when (repository.deleteAll()) {
            is SuspendedResult.Success -> {
                Assert.assertEquals(
                    "Did not receive empty list",
                    emptyList<Plant>(), db.plantDao().getAll()
                )
            }
            else -> Assert.fail("Operation was not successful")
        }
    }
}