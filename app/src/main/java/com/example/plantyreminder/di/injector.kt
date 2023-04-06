package com.example.plantyreminder.di

import com.example.plantyreminder.persistance.PlantsRepository
import com.example.plantyreminder.persistance.PlantsRoomRepository
import com.example.plantyreminder.views.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<PlantsRepository> { PlantsRoomRepository(androidContext()) }

    viewModel { HomeViewModel( get() ) }
}
