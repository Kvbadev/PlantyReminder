package com.example.plantyreminder

import PlantSlider
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantyreminder.data.models.Plant
import com.example.plantyreminder.data.models.PlantTimespan
import com.example.plantyreminder.ui.theme.PlantyReminderTheme
import com.example.plantyreminder.views.HomeViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantyReminderTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PlantSlider();
                }
            }
        }
    }
}

object SampleData {
    val plantsSample = listOf(
        Plant(
            "African Violet", PlantTimespan(3, 6), 21, "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-bC6B64133c0743b34224/i-0-ymxg64133c07444a4224.jpg"
        ), Plant(
            "Cleistocactus", PlantTimespan(2, 7), 16, "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-kkog64133e50146a6224/i-0-rtsa64133e5014e74224.jpg"
        ), Plant(
            "White Japanese Strawberry", PlantTimespan(6, 8), 11, "https://perenual.com/storage/marketplace/3-Whimsy%20and%20Wonder%20Seeds/p-pweY64138348e6ce81/i-0-7vjl64138348e6d801.jpg"
        )
    )
}
