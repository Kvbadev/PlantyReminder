package com.example.plantyreminder.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.plantyreminder.MainActivity
import org.koin.androidx.compose.getViewModel
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.PlantyScreen

@Composable
fun Home(navController: NavController) {
    val homeViewModel = getViewModel<HomeViewModel>()
    val errorState by homeViewModel.errorState.collectAsState()
    val loadingPlantsState by homeViewModel.loadingPlantsState.collectAsState()
    val plants by homeViewModel.plants.collectAsState()

    Surface(
        Modifier.fillMaxSize()
    ) {
        Scaffold(
            floatingActionButton = { AddPlant(navController) },
            isFloatingActionButtonDocked = true
        ) { padding ->
            Column(Modifier.padding(padding)) {
                if (!loadingPlantsState) {
                    PlantSlider(plants)

                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .align(Alignment.Center),
                            color = colorResource(
                                id = R.color.blue_700
                            )
                        )
                    }
                    if (errorState != null && MainActivity.isActivityVisible) {
                        Toast.makeText(
                            LocalContext.current,
                            errorState!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}

@Composable
fun AddPlant(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(PlantyScreen.Add.name)
        },
        backgroundColor = colorResource(id = R.color.green_700),
        contentColor = colorResource(id = R.color.white)
    ) {
        Icon(Icons.Filled.Add, "")
    }
}


