package com.example.plantyreminder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.details.Details
import com.example.plantyreminder.ui.details.DetailsViewModel
import com.example.plantyreminder.ui.home.Home
import com.example.plantyreminder.ui.search.Search
import org.koin.androidx.compose.getViewModel


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
                    { id -> navController.navigate("${PlantyScreen.Details.name}/$id") },
                    { navController.navigate(PlantyScreen.Home.name) },
                )
            }
            composable(
                "${PlantyScreen.Details.name}/{plantId}",
                arguments = listOf(navArgument("plantId") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("plantId")
                Details(id)
            }
        }
    }
}
