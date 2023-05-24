package com.example.plantyreminder.data.persistance

import android.content.Context
import android.database.sqlite.SQLiteCantOpenDatabaseException
import android.database.sqlite.SQLiteConstraintException
import com.example.plantyreminder.domain.*
import kotlinx.coroutines.delay
import javax.inject.Inject

class PlantsRoomRepository @Inject constructor(
    private val plantDao: PlantDao
) : PlantsRepository {
    override suspend fun getAll(): SuspendedResult<List<Plant>> {
        val data: List<Plant>
        return try {
            data = plantDao.getAll()
            SuspendedResult.Success(data)

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        }
    }

    override suspend fun insertAll(plants: List<Plant>): SuspendedResult<Unit> {
        return try {
            val res = plantDao.insertAll(plants)
            SuspendedResult.Success(res)

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        }
    }

    override suspend fun insert(plant: Plant): SuspendedResult<Long> {
        return try {
            val res = plantDao.insert(plant)
            SuspendedResult.Success(res)

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        }
    }

    override suspend fun update(plant: Plant): SuspendedResult<Unit> {
        return try {
            val res = plantDao.update(plant)
            SuspendedResult.Success(res)

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        }
    }

    override suspend fun delete(plant: Plant): SuspendedResult<Unit> {
        return try {
            SuspendedResult.Success(plantDao.delete(plant))

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        }
    }

    override suspend fun deleteAll(): SuspendedResult<Unit> {
        return try {
            val res = plantDao.deleteAll()
            SuspendedResult.Success(res)

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        }
    }

    override suspend fun getPlant(uid: Long): SuspendedResult<Plant?> {
        return try {
            val res = plantDao.getPlant(uid)
            SuspendedResult.Success(res)

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        }
    }
}