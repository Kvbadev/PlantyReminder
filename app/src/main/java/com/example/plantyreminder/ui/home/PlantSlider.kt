@file:OptIn(
    ExperimentalFoundationApi::class
)

package com.example.plantyreminder.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.SubcomposeAsyncImage
import com.example.plantyreminder.R
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.PlantWateringSpan
import com.example.plantyreminder.domain.SunPreference
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.absoluteValue

@Composable
fun PlantSlider(plants: List<Plant>) {
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
            contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 0.dp)
        ) {
            PlantItem(plant = plants[it])
        }
    }
}

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }


@Composable
fun PlantItem(plant: Plant) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
    ) {
        Box(
            modifier = Modifier
                .height(900.pxToDp())
                .fillMaxWidth()
        ) {

            SubcomposeAsyncImage(
                model = plant.imageUrl,
                contentDescription = plant.name,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                loading = { CircularProgressIndicator() }
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp)
        ) {
            Text(
                text = plant.name,
                color = colorResource(id = R.color.blue_700),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 32.sp,
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
                value = "${plant.waterSpan.getEstimatedTimespan()} days",
                label = "Watering",
                Modifier.weight(1f)
            )
            PlantItemMetric(
                value = plant.origin,
                label = "Origin",
                Modifier.weight(1f)
            )
            PlantItemMetric(
                value = plant.sunlight,
                label = "Sunlight",
                Modifier.weight(1f)
            )
        }
        ItemNextWatering(ChronoUnit.DAYS.between(LocalDate.now(), plant.nextWatering))
    }
}

@Composable
fun ItemNextWatering(daysToWatering: Long) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)
            .border(2.dp, colorResource(id = R.color.blue_700), RoundedCornerShape(6.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Next watering: ",
            fontSize = 24.sp,
        )
        Text(
            text = when {
                daysToWatering > 0 -> "$daysToWatering days"
                daysToWatering == 0L -> "Today"
                else -> "${daysToWatering.absoluteValue} days ago!"
            },
            color = colorResource(id = if (daysToWatering > 0) R.color.blue_700 else R.color.red_500),
            fontSize = 24.sp,
            textAlign = TextAlign.End,
        )
    }
}

@Composable
inline fun <reified T> PlantItemMetric(
    value: T,
    label: String,
    columnModifier: Modifier = Modifier
) {
    val isText = T::class.java == String::class.java

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
        if (isText && value is String) {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                color = colorResource(id = R.color.gray_700),
                text = value,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
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
    PlantSlider(plants = emptyList())
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
