@file:OptIn(
    ExperimentalFoundationApi::class
)

package com.example.plantyreminder.ui.home

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.plantyreminder.R
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantWateringSpan
import com.example.plantyreminder.domain.SunPreference
import com.example.plantyreminder.domain.UiEvent
import com.example.plantyreminder.ui.AsyncImageHandler
import com.example.plantyreminder.ui.ExtendableText
import com.example.plantyreminder.utils.pxToDp
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.absoluteValue

@Composable
fun PlantSlider(plants: List<Plant>) {
    val viewModel: HomeViewModel = hiltViewModel()
    var updatingPlant by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.updatingEvent.collectLatest {
            when (it) {
                UiEvent.Loading -> updatingPlant = true
                UiEvent.Success -> {
                    updatingPlant = false
                    Toast.makeText(
                        context,
                        "Plant updated!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    if (plants.isEmpty()) {
        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.plant_image),
                contentDescription = "Plant image",
            )
            Text(
                text = "You have no plants yet!",
                fontSize = 16.sp,
                color = colorResource(id = R.color.gray_700),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
    } else {
        HorizontalPager(
            pageCount = plants.size,
            contentPadding = PaddingValues(0.dp, 10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PlantItem(plant = plants[it], viewModel::updatePlant)
        }
    }
}


@Composable
fun PlantItem(plant: Plant, updatePlant: (Plant, Context) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(plant.imageUrl)
                .build(),
            contentDescription = plant.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(900.pxToDp())
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        ) {
            AsyncImageHandler(subcomposeAsyncImageScope = this, boxSize = 128.dp)
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp)
        ) {
            ExtendableText(
                text = plant.name,
                textStyle = TextStyle(
                    color = colorResource(id = R.color.green_700),
                    fontSize = 32.sp,
                )
            )
            Text(
                text = "${ChronoUnit.DAYS.between(plant.createdAt, LocalDate.now())} days",
                color = colorResource(id = R.color.blue_700),
                maxLines = 1,
                fontSize = 16.sp,
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            PlantItemMetric(
                value = "${plant.waterSpan?.getEstimatedTimespan()} days",
                label = "Watering",
                Modifier.weight(1f)
            )
            PlantItemMetric(
                value = plant.sunlight,
                label = "Sunlight",
                Modifier.weight(2f)
            )
            PlantItemMetric(
                value = plant.origin?.first(),
                label = "Origin",
                Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            PlantItemMetric(
                value = if (plant.indoor == true) "Yes" else "No",
                label = "Indoor",
                Modifier.weight(1f)
            )
            PlantItemMetric(
                value = plant.type,
                label = "Type",
                Modifier.weight(2f)
            )
            PlantItemMetric(
                value = if (plant.edible == true) "Yes" else "No",
                label = "Edible",
                Modifier.weight(1f)
            )
        }
        Spacer(Modifier.padding(8.dp))
        ItemNextWatering(plant, updatePlant)
    }
}

@Composable
fun ItemNextWatering(plant: Plant, updatePlant: (Plant, Context) -> Unit) {
    var daysToWatering by rememberSaveable {
        mutableStateOf(
            ChronoUnit.DAYS.between(
                LocalDate.now(),
                plant.nextWatering
            )
        )
    }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val newValue = ChronoUnit.DAYS.between(
                    LocalDate.now(),
                    LocalDate
                        .now()
                        .plusDays(plant.waterSpan?.getEstimatedTimespan() ?: 0L)
                )
//                if (newValue <= daysToWatering) return@clickable
                daysToWatering = newValue
                plant.nextWatering = plant.nextWatering.plusDays(newValue)
                updatePlant(plant, context)
            },
        elevation = 8.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Crossfade(
                targetState = daysToWatering,
                animationSpec = tween(1000),
            ) {
                Text(
                    text = when {
                        it > 0 -> "$daysToWatering days"
                        it == 0L -> "Today"
                        else -> "${it.absoluteValue} days ago!"
                    },
                    color = colorResource(
                        id = when {
                            it > 0 -> R.color.green_200
                            it == 0L -> R.color.black
                            else -> R.color.red_500
                        }
                    ),
                    fontSize = 24.sp,
                    textAlign = TextAlign.End,
                )
            }
            Image(
                painter = painterResource(R.drawable.watering_can_icon),
                contentDescription = "Next watering",
                modifier = Modifier.size(128.dp)
            )
        }
    }
}

@Composable
inline fun <reified T> PlantItemMetric(
    value: T,
    label: String,
    columnModifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(2.dp)
            .then(columnModifier),

        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            color = colorResource(id = R.color.gray_500),
            textAlign = TextAlign.Start,
        )
        if (value is String) {
            Spacer(modifier = Modifier.padding(4.dp))
            ExtendableText(
                text = value,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.gray_700)
                )
            )
        } else if (value is List<*>) {
            Row {
                SunlightImageButton(value.first() as SunPreference)
                if (value.size > 1) {
                    Text(text = "/", fontSize = 34.sp)
                    SunlightImageButton(value[1] as SunPreference)
                }
            }
        }
    }
}

@Composable
fun SunlightImageButton(preference: SunPreference) {
    val popupControl = remember { mutableStateOf(false) }
    val context = LocalContext.current
    IconButton(
        onClick = { popupControl.value = true },
    )
    {
        Image(
            painter = painterResource(
                id = context.resources.getIdentifier(
                    preference.toString().lowercase(),
                    "drawable",
                    context.packageName
                )
            ),
            contentDescription = "Watering Span",
        )
    }
    if (popupControl.value) {
        DescriptionPopup(popupControl, preference.description)
    }
}

@Composable
fun DescriptionPopup(popupControl: MutableState<Boolean>, description: String) {
    Popup(
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            clippingEnabled = true,
        ),
        onDismissRequest = { popupControl.value = false },
        offset = IntOffset(-30, 60)
    ) {
        Box(
            modifier = Modifier
                .background(
                    colorResource(id = R.color.gray_200),
                    RoundedCornerShape(20.dp)
                )
                .width(200.dp)
        ) {
            Text(
                text = description,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(6.dp),
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantSliderPreview() {
    PlantSlider(plants = SampleData.plantsSample)
}

@Preview(showBackground = true)
@Composable
fun PlantSliderPreviewEmpty() {
    PlantItem(SampleData.plantsSample[0]) { _, _ ->}
}

object SampleData {
    val plantsSample = listOf(
        Plant(
            uid = 1,
            name = "African Violet",
            "",
            PlantWateringSpan.FREQUENT,
            sunlight = listOf(SunPreference.FULL_SHADE, SunPreference.PART_SHADE),
            imageUrl = "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-bC6B64133c0743b34224/i-0-ymxg64133c07444a4224.jpg",
            createdAt = LocalDate.now().minusDays(7),

            ), Plant(
            uid = 2,
            "Cleistocactus",
            "",
            PlantWateringSpan.AVERAGE,
            listOf(SunPreference.FULL_SUN, SunPreference.FULL_SHADE),
            "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-kkog64133e50146a6224/i-0-rtsa64133e5014e74224.jpg",
            createdAt = LocalDate.now().minusDays(3),
        ), Plant(
            uid = 3,
            "White Japanese Strawberry",
            "",
            PlantWateringSpan.MINIMUM,
            listOf(SunPreference.PART_SHADE),
            "https://perenual.com/storage/marketplace/3-Whimsy%20and%20Wonder%20Seeds/p-pweY64138348e6ce81/i-0-7vjl64138348e6d801.jpg",
        )
    )
    val searchResultSample: List<PlantSearchResult> = listOf(
        PlantSearchResult(listOf("Cactus Cactus Cactus", "lksj lskdjfl lsdjfklsldf"), ""),
        PlantSearchResult(listOf("Strawberry"), ""),
        PlantSearchResult(listOf("Hibiscus"), ""),
    )
}
