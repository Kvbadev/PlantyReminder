package com.example.plantyreminder.di

import com.example.plantyreminder.data.api.ApiClient
import com.example.plantyreminder.data.api.ApiClientFactory
import com.example.plantyreminder.domain.PlantsRepository
import com.example.plantyreminder.data.persistance.PlantsRoomRepository
import com.example.plantyreminder.ui.details.DetailsViewModel
import com.example.plantyreminder.ui.home.HomeViewModel
import com.example.plantyreminder.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<PlantsRepository> { PlantsRoomRepository(androidContext()) }
    factory { ApiClientFactory().createGsonApiClient() }
    viewModel {
        val model = HomeViewModel(get())
        model.updateUserPlants()
        model
    }
    viewModel { SearchViewModel(get()) }
    viewModel {params ->
        val model = DetailsViewModel(get())
        model.getDetailedPlant(params.get())
        model
    }
}
