package com.example.plantyreminder.ui.details

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.R
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.ui.FullScreenLoader
import com.example.plantyreminder.ui.home.SampleData
import com.example.plantyreminder.ui.home.pxToDp
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Details(id: Int?) {
    val detailsViewModel: DetailsViewModel = getViewModel(parameters = { parametersOf(id) })
    val plant by detailsViewModel.plant.collectAsState()
    val error by detailsViewModel.errorState.collectAsState()
    val loading by detailsViewModel.loadingState.collectAsState()

    Column(Modifier.fillMaxSize()) {
        if (!loading) {
            PlantDetail(
                plant = plant ?: throw Exception("Plant was null")

            ) {
                detailsViewModel.addPlantToLibrary(
                    plant = plant ?: throw Exception("Plant was null")
                )
            }

        } else FullScreenLoader()

        if (error != null && MainActivity.isActivityVisible) {
            Toast.makeText(
                LocalContext.current, error!!.message, Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Composable
fun PlantDetail(plant: Plant, addPlantToSaved: () -> Unit) {
    Box(
        modifier = Modifier
            .height(320.dp)
            .fillMaxWidth()
            .padding(8.dp, 10.dp, 8.dp, 8.dp)
    ) {
        SubcomposeAsyncImage(
            model = plant.imageUrl,
            contentDescription = plant.name,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            loading = { CircularProgressIndicator() }
        )
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
    ) {
        Text(
            text = plant.name,
            color = colorResource(id = R.color.green_700),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 32.sp,
        )
        Spacer(Modifier.padding(2.dp))
        Text(
            text = plant.description,
            color = colorResource(id = R.color.black),
            fontSize = 16.sp,
        )
        Spacer(Modifier.padding(10.dp))
        PlantParameter(label = "Origin", value = plant.origin?.first())
        PlantParameter(label = "Family", value = plant.family)
        PlantParameter(label = "type", value = plant.type)
        PlantParameter(label = "edible", value = (plant.edible ?: "N/A").toString())
        AddToPlants(addPlantToSaved)
    }


}

@Composable
fun AddToPlants(addPlantToSaved: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Button(
            onClick = {
                addPlantToSaved()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        ) {
            Text(text = "Add to ")
            Text(text = "your plants", color = colorResource(id = R.color.green_500))
        }
    }
}

@Composable
fun PlantParameter(label: String, value: String?) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 8.dp)
    ) {
        Text(
            text = label,
            color = colorResource(id = R.color.green_700),
            fontSize = 20.sp,
            modifier = Modifier.weight(3f),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = value ?: "N/A",
            color = colorResource(id = R.color.green_500),
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(3f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PlantDetailPreview() {
    PlantDetail(plant = SampleData.plantsSample[0]){}
}