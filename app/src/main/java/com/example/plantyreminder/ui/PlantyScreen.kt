package com.example.plantyreminder.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.home.Home
import com.example.plantyreminder.ui.search.Search

enum class PlantyScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Add(title = R.string.add_plant),
    Settings(title = R.string.settings)
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
                Home(navController)
            }
            composable(PlantyScreen.Add.name) {
                Search()
            }
        }
    }
}
