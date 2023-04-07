package com.example.plantyreminder.views.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun Home() {
    val homeViewModel = getViewModel<HomeViewModel>()
//    homeViewModel.addUserPlants()
//    homeViewModel.removeAllPlants()
    val plants = homeViewModel.getUserPlants()
    val errorState by homeViewModel.errorState.collectAsState()

    Surface(
        Modifier
            .fillMaxSize()
    ) {
        PlantSlider(plants)
        if(errorState != null) {
            Text(text = errorState!!.message())
        }
    }

}