package com.example.plantyreminder.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantyreminder.data.dto.PlantSearchResult
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.ui.home.SampleData

@Composable
fun PlantsList(
    plants: List<PlantSearchResult>
) {
    Surface(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(plants) {
                Row {
                    it.names.forEach {
                        Text(
                            text = it, Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                    Text(
                        text = "${it.watering.getTimespan()} days",
                        Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.width(20.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun PlantSliderPreview() {
//    PlantsList(SampleData.plantsSample);
}
