package com.example.plantyreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.plantyreminder.persistance.PlantDatabase
import com.example.plantyreminder.persistance.PlantsRepository
import com.example.plantyreminder.persistance.PlantsRoomRepository
import com.example.plantyreminder.ui.theme.PlantyReminderTheme
import com.example.plantyreminder.views.home.Home

class MainActivity : ComponentActivity() {
    companion object {
        val REPOSITORY_DEPENDENCY = PlantsRoomRepository();
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializing the repository
        REPOSITORY_DEPENDENCY.initialize(applicationContext)

        setContent {
            PlantyReminderTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.white))
                ) {
                    Home();
                }
            }
        }
    }
}
