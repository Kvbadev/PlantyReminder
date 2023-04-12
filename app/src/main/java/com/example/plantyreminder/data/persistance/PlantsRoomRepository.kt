package com.example.plantyreminder.data.persistance

import android.content.Context
import android.database.sqlite.SQLiteCantOpenDatabaseException
import android.database.sqlite.SQLiteConstraintException
import com.example.plantyreminder.data.PlantsRepository
import com.example.plantyreminder.domain.*
import java.util.logging.ErrorManager

class PlantsRoomRepository(
    context: Context,
    private val database: PlantDatabase = PlantDatabase.getInstance(context)
) : PlantsRepository {

    override suspend fun getAll(): SuspendedResult<List<Plant>> {
        val data: List<Plant>
        return try {
            data = database.plantDao().getAll()
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
            val res = database.plantDao().insertAll(plants)
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
            val res = database.plantDao().insert(plant)
            SuspendedResult.Success(res)

        } catch (e: SQLiteConstraintException) {
            SuspendedResult.Error(ErrorEntity.Database.ConstraintException)
        } catch (e: SQLiteCantOpenDatabaseException) {
            SuspendedResult.Error(ErrorEntity.Database.CantOpenException)
        } catch (e: Exception) {
            SuspendedResult.Error(ErrorEntity.Default.Unknown)
        }
    }

    override fun delete(plant: Plant) {
//        return database.plantDao().delete(plant)
    }

    override suspend fun deleteAll(): SuspendedResult<Unit> {
        return try {
            val res = database.plantDao().deleteAll()
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