package com.example.plantyreminder.views.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.plantyreminder.MainActivity
import org.koin.androidx.compose.getViewModel
import com.example.plantyreminder.R

@Composable
fun Home() {
    val homeViewModel = getViewModel<HomeViewModel>()
    val plants = homeViewModel.getUserPlants()
    val errorState by homeViewModel.errorState.collectAsState()
//    homeViewModel.removeAllPlants()
//    homeViewModel.addUserPlants(SampleData.plantsSample)

    Surface(
        Modifier
            .fillMaxSize()
    ) {
        Column {

            PlantSlider(plants)
            if (errorState != null && MainActivity.isActivityVisible) {
                Toast.makeText(LocalContext.current, errorState!!.message, Toast.LENGTH_LONG).show()
            }
            AddPlant(plants.isNotEmpty())
        }
    }

}

@Composable
fun AddPlant(hasPlants: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {

        Button(
            onClick = {

            }, modifier = Modifier
                .width(250.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Add ${if (hasPlants) "another plant" else "first plant"}",
            )
        }
    }
}
