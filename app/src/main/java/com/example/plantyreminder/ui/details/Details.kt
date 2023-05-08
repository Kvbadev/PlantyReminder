package com.example.plantyreminder.ui.details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.R
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.UiEvent
import com.example.plantyreminder.ui.ExtendableText
import com.example.plantyreminder.ui.FullScreenLoader
import com.example.plantyreminder.ui.home.SampleData
import com.example.plantyreminder.ui.home.pxToDp
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Details(id: Int?, navigateBack: () -> Unit) {
    val detailsViewModel: DetailsViewModel = getViewModel(parameters = { parametersOf(id) })

    val plant by detailsViewModel.plant.collectAsState()
    val error by detailsViewModel.errorState.collectAsState()
    val loading by detailsViewModel.loadingState.collectAsState()
    val context = LocalContext.current
    var operationLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        detailsViewModel.operationEvent.collectLatest {
            when (it) {
                is UiEvent.Loading -> operationLoading = true
                is UiEvent.Success -> {
                    operationLoading = false
                    Toast.makeText(
                        context,
                        "Plant with id ${it.id} has been added!",
                        Toast.LENGTH_LONG
                    ).show()
                    navigateBack()
                }
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        if (!loading) {
            PlantDetail(
                plant = plant ?: throw Exception("Plant was null"),
                operationLoading
            ) {
                detailsViewModel.addPlantToLibrary(
                    plant = plant ?: throw Exception("Plant was null")
                )
            }

        } else FullScreenLoader()

        if (error != null && MainActivity.isActivityVisible) {
            Toast.makeText(
                context, error!!.message, Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Composable
fun PlantDetail(plant: Plant, operationLoading: Boolean, addPlantToSaved: () -> Unit) {

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp, 0.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
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
        Column {
            ExtendableText(
                text = plant.name, textStyle = TextStyle(
                    color = colorResource(id = R.color.green_700),
                    fontSize = 32.sp,
                )
            )
            Text(
                text = if (plant.description != "") plant.description else "No Description",
                color = colorResource(id = R.color.black),
                fontSize = 16.sp,
            )
        }
        Spacer(Modifier.padding(2.dp))
        PlantParameter(label = "Origin", value = plant.origin?.first())
        PlantParameter(label = "Family", value = plant.family)
        PlantParameter(label = "type", value = plant.type)
        PlantParameter(label = "edible", value = (plant.edible ?: "N/A").toString())
        AddToPlants(operationLoading, addPlantToSaved)
    }


}

@Composable
fun AddToPlants(operationLoading: Boolean, addPlantToSaved: () -> Unit) {
    Button(
        onClick = {
            addPlantToSaved()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(40.dp),
    ) {
        Text(text = "Add to ", color = colorResource(id = R.color.black))
        Text(text = "your plants", color = colorResource(id = R.color.white))
        if (operationLoading) {
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                Modifier
                    .height(40.dp)
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.black),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .size(24.dp)
                )
            }
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
    PlantDetail(plant = SampleData.plantsSample[0], true) {}
}