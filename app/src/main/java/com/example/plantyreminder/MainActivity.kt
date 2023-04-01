package com.example.plantyreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.plantyreminder.ui.theme.PlantyReminderTheme
import com.example.plantyreminder.views.home.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantyReminderTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Home();
                }
            }
        }
    }
}
