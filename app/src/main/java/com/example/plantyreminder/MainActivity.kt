package com.example.plantyreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.plantyreminder.ui.PlantyReminderApp
import com.example.plantyreminder.ui.theme.PlantyReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlantyReminderTheme {
                PlantyReminderApp()
            }
        }
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
