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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantyreminder.MainActivity
import org.koin.androidx.compose.getViewModel
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.FullScreenLoader

@Composable
fun Home(onNavigateToSearch: () -> Unit) {
    val homeViewModel = getViewModel<HomeViewModel>()
    val plants by homeViewModel.results.collectAsState()
    val error by homeViewModel.errorState.collectAsState()
    val loading by homeViewModel.loadingState.collectAsState()

    Surface(
        Modifier.fillMaxSize()
    ) {
        Scaffold(
            floatingActionButton = { AddPlant(onNavigateToSearch) },
            isFloatingActionButtonDocked = true
        ) { padding ->
            Column(Modifier.padding(padding)) {
                if (!loading) {
                    PlantSlider(plants)

                } else FullScreenLoader()

                if (error != null && MainActivity.isActivityVisible) {
                Toast.makeText(
                    LocalContext.current,
                    error!!.message,
                    Toast.LENGTH_LONG
                ).show()
            }
            }
        }
    }
}

@Composable
fun AddPlant(navigateToSearch: () -> Unit) {
    FloatingActionButton(
        onClick = {
            navigateToSearch()
        },
        backgroundColor = colorResource(id = R.color.green_700),
        contentColor = colorResource(id = R.color.white)
    ) {
        Icon(Icons.Filled.Add, "")
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home {}
}


