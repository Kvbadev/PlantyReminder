package com.example.plantyreminder.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.home.Home
import com.example.plantyreminder.ui.search.Search


enum class PlantyScreen {
    Home, Search, Details, Settings
}


@Composable
fun PlantyReminderApp() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        NavHost(
            navController = navController,
            startDestination = PlantyScreen.Home.name,
        ) {
            composable(PlantyScreen.Home.name) {
                Home {
                    navController.navigate(PlantyScreen.Search.name)
                }
            }
            composable(PlantyScreen.Search.name) {
                Search(
                    { navController.navigate(PlantyScreen.Home.name) },
                    { navController.navigate("${PlantyScreen.Details.name}/{plantId}") }
                )
            }
        }
    }
}
