package com.example.plantyreminder.views.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.plantyreminder.R

@Composable
fun Home() {
    val viewModel = HomeViewModel()

    Surface(Modifier.fillMaxSize()) {
        PlantSlider(plants = viewModel.getUserPlants())
    }

}