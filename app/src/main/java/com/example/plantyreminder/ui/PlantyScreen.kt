package com.example.plantyreminder.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.details.Details
import com.example.plantyreminder.ui.home.Home
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
                    Home(
                        openDrawer = { openDrawer() }
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
                    val id = it.arguments?.getInt("plantId")
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
