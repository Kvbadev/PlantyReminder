package com.example.plantyreminder.data.persistance

import android.content.Context
import android.database.sqlite.SQLiteCantOpenDatabaseException
import android.database.sqlite.SQLiteConstraintException
import com.example.plantyreminder.domain.*
import kotlinx.coroutines.delay

class PlantsRoomRepository(
    context: Context,
    private val database: PlantDatabase = PlantDatabase.getInstance(context),
    private val plantDao: PlantDao = database.plantDao()
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
        } catch (e: Exception) {
            val error = ErrorEntity.Default.Unknown
            error.message = e.message.toString()

            SuspendedResult.Error(ErrorEntity.Default.Unknown)
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
        } catch (e: Exception) {
            SuspendedResult.Error(ErrorEntity.Default.Unknown)
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
        } catch (e: Exception) {
            SuspendedResult.Error(ErrorEntity.Default.Unknown)
        }
    }

    override suspend fun delete(plant: Plant): SuspendedResult<Unit> {
        return try {
            SuspendedResult.Success(plantDao.delete(plant))

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        } catch (e: Exception) {
            SuspendedResult.Error(ErrorEntity.Default.Unknown)
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
        } catch (e: Exception) {
            SuspendedResult.Error(ErrorEntity.Default.Unknown)
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
        } catch (e: Exception) {
            SuspendedResult.Error(ErrorEntity.Default.Unknown)
        }
    }
}