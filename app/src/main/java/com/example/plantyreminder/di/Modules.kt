package com.example.plantyreminder.di

import android.content.Context
import androidx.room.Room
import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.data.api.ApiClientFactory
import com.example.plantyreminder.data.persistance.PlantDao
import com.example.plantyreminder.data.persistance.PlantDatabase
import com.example.plantyreminder.data.persistance.PlantsRoomRepository
import com.example.plantyreminder.domain.PlantsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPlantsRepository(
        plantsRoomRepository: PlantsRoomRepository
    ) : PlantsRepository
}

@Module
@InstallIn(ViewModelComponent::class)
object ApiModule {

    @Provides
    fun provideApiClient() : ApiClient {
        val factory = ApiClientFactory()
        return factory.createGsonApiClient()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providePlantDao(
        plantDatabase: PlantDatabase
    ) : PlantDao {
        return plantDatabase.plantDao()
    }

    @Singleton
    @Provides
    fun providePlantDatabase(
        @ApplicationContext context: Context
    ) : PlantDatabase {
        return Room.databaseBuilder(context,
            PlantDatabase::class.java,
            "plants"
        ).build()
    }
}
