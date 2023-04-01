@file:OptIn(ExperimentalFoundationApi::class)

package com.example.plantyreminder.views.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.plantyreminder.data.models.Plant
import com.example.plantyreminder.data.models.PlantTimespan

@Composable
fun PlantSlider(plants: List<Plant>) {

    HorizontalPager(
        pageCount = plants.size,
        contentPadding = PaddingValues(0.dp, 10.dp)
    ) {
        PlantItem(plant = plants[it])
    }
}

@Composable
fun PlantItem(plant: Plant) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp, 0.dp)
            .height(450.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(Modifier.height(400.dp)) {
            AsyncImage(
                model = plant.imageUrl,
                contentDescription = "Plant's image",
                Modifier.align(
                    Alignment.Center
                )
            )
        }
        Box(Modifier.border(2.dp, Color.Black).fillMaxWidth()) {
            Text(
                text = plant.name,
                Modifier
                    .padding(8.dp)

                    .align(Alignment.Center),
                fontSize = 24.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantSliderPreview() {
    PlantSlider(plants = SampleData.plantsSample)
}


object SampleData {
    val plantsSample = listOf(
        Plant(
            "African Violet",
            PlantTimespan(3, 6),
            21,
            "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-bC6B64133c0743b34224/i-0-ymxg64133c07444a4224.jpg"
        ), Plant(
            "Cleistocactus",
            PlantTimespan(2, 7),
            16,
            "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-kkog64133e50146a6224/i-0-rtsa64133e5014e74224.jpg"
        ), Plant(
            "White Japanese Strawberry",
            PlantTimespan(6, 8),
            11,
            "https://perenual.com/storage/marketplace/3-Whimsy%20and%20Wonder%20Seeds/p-pweY64138348e6ce81/i-0-7vjl64138348e6d801.jpg"
        )
    )
}
