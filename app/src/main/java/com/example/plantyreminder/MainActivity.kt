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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
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


    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }

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

}
