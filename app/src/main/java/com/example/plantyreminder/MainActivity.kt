package com.example.plantyreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.plantyreminder.di.appModule
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.ui.theme.PlantyReminderTheme
import com.example.plantyreminder.views.home.Home
import com.example.plantyreminder.views.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {

    companion object {
        var isActivityVisible: Boolean = false
    }

    override fun onResume() {
        super.onResume()
        isActivityVisible = !isActivityVisible
    }

    override fun onPause() {
        super.onPause()
        isActivityVisible = !isActivityVisible
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            PlantyReminderTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.white))
                ) {
                    Scaffold(
                        floatingActionButton = {
                            AddPlant()
                        },
                        isFloatingActionButtonDocked = true,
                        bottomBar = {
                            BottomNavigation()
                        }
                    ) { padding ->
                        Column {
                            Modifier.padding(padding)
                            Home()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }

    @Composable
    fun BottomNavigation() {
        BottomAppBar(
            backgroundColor = colorResource(id = R.color.green_500)
        ) {
            IconButton(onClick = { /* Check onClick */ }) {
                Icon(Icons.Filled.Home, contentDescription = "", tint = Color.White)
            }
            IconButton(onClick = { /* Edit onClick */ }) {
                Icon(
                    Icons.Filled.Call, contentDescription = "", tint = Color.White
                )
            }
            IconButton(onClick = { /* Delete onClick */ }) {
                Icon(Icons.Filled.Search, contentDescription = "", tint = Color.White)
            }
        }
    }

    @Composable
    fun AddPlant() {
        FloatingActionButton(
            onClick = {

            },
            backgroundColor = colorResource(id = R.color.gray_700),
            contentColor = colorResource(id = R.color.white)
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }
}
