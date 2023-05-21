package com.example.plantyreminder.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.ui.FullScreenLoader
import com.example.plantyreminder.ui.navigation.TopBar

@Composable
fun Home(
    homeViewModel: HomeViewModel = hiltViewModel(),
    openDrawer: () -> Unit
) {
    val plants by homeViewModel.results.collectAsState()
    val error by homeViewModel.errorState.collectAsState()
    val loading by homeViewModel.loadingState.collectAsState()

    Surface(
        Modifier.fillMaxSize()
    ) {
        Column {
            TopBar(
                buttonIcon = Icons.Filled.Menu,
                title = "Home",
                onButtonClicked = { openDrawer() }
            )
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

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    Home() {}
//}


