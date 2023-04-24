package com.example.plantyreminder.ui.search

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plantyreminder.data.dto.PlantSearchResult
import com.example.plantyreminder.ui.home.SampleData
import com.example.plantyreminder.R

@Composable
fun PlantsList(
    plants: List<PlantSearchResult>
) {
    LazyColumn(Modifier.padding(8.dp)) {
        items(plants) {
            Row(
                Modifier
                    .padding(0.dp, 2.dp)
                    .border(2.dp, colorResource(id = R.color.green_700))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(3f)) {
                    Text(it.names.first(), color = colorResource(id = R.color.black))
                    it.names.drop(1).forEach {
                        Text(
                            text = it,
                            color = colorResource(id = R.color.blue_500)
                        )
                    }

                }
                Text(
                    text = "${it.watering.getTimespan()} days",
                    Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun PlantSliderPreview() {
    PlantsList(SampleData.searchResultSample);
}
