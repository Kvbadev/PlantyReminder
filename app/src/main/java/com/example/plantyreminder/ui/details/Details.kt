package com.example.plantyreminder.ui.details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.R
import com.google.gson.Gson
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Details(id: Int?) {
    val detailsViewModel: DetailsViewModel = getViewModel(parameters = { parametersOf(id) })
    val plant by detailsViewModel.plant.collectAsState()
    val error by detailsViewModel.errorState.collectAsState()
    val loading by detailsViewModel.loadingState.collectAsState()

    val details = Gson().toJson(plant).split(",").toList()

    Column(Modifier.fillMaxSize()) {
        if (!loading) {
            details.forEach {
                PlantDetail(detail = it)
            }
        } else {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier
                        .align(Alignment.Center)
                        .size(100.dp),
                    color = colorResource(id = R.color.blue_700)
                )
            }
        }
    }

    if (error != null && MainActivity.isActivityVisible) {
        Toast.makeText(
            LocalContext.current, error!!.message, Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
fun PlantDetail(detail: String) {
    Row(Modifier.fillMaxWidth()) {
        Text(detail, fontSize = 24.sp)
    }
}