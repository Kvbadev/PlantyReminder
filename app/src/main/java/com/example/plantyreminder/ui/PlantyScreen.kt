package com.example.plantyreminder.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.details.Details
import com.example.plantyreminder.ui.home.Home
import com.example.plantyreminder.ui.home.HomeViewModel
import com.example.plantyreminder.ui.navigation.Drawer
import com.example.plantyreminder.ui.search.Search
import com.example.plantyreminder.ui.settings.Settings
import kotlinx.coroutines.launch

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
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.open()
            }
        }

        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    })
            }
        ) {

            NavHost(
                navController = navController,
                startDestination = PlantyScreen.Home.name,
            ) {
                composable(PlantyScreen.Home.name) {
                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    Home(
                        openDrawer = { openDrawer() },
                        homeViewModel = homeViewModel
                    )
                }
                composable(PlantyScreen.Search.name) {
                    Search(
                        navigateTo = { route ->
                            navController.navigate(route)
                        },
                        openDrawer = { openDrawer() }
                    )
                }
                composable(
                    "${PlantyScreen.Details.name}/{plantId}",
                    arguments = listOf(navArgument("plantId") { type = NavType.IntType })
                ) {
                    val id = it.arguments?.getInt("plantId") ?: return@composable
                    Details(
                        id,
                        navigateTo = {route -> navController.navigate(route)}
                    )
                }
                composable(PlantyScreen.Settings.name) {
                    Settings {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}
