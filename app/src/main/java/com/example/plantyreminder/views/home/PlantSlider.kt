@file:OptIn(
    ExperimentalFoundationApi::class
)

package com.example.plantyreminder.views.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import coil.compose.AsyncImage
import com.example.plantyreminder.R
import com.example.plantyreminder.data.Plant
import com.example.plantyreminder.data.PlantTimespan
import com.example.plantyreminder.data.SunPreference
import java.util.*
import kotlin.time.Duration

@Composable
fun PlantSlider(plants: List<Plant>) {
    HorizontalPager(
        pageCount = plants.size, contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 0.dp),
        modifier = Modifier.padding(16.dp, 0.dp)
    ) {
        PlantItem(plant = plants[it])
    }
}

@Composable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }

@Composable
fun PlantItem(plant: Plant) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = plant.imageUrl,
            contentDescription = "Plant's image",
            Modifier
                .clip(
                    shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
                )
                .height(900.pxToDp())
                .fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = plant.name,
                Modifier
                    .padding(0.dp, 6.dp)
                    .weight(3f),
                color = colorResource(id = R.color.blue_700),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 32.sp,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = plant.age.toString() + " days",
                modifier = Modifier
                    .padding(0.dp, 6.dp)
                    .weight(1f),
                color = colorResource(id = R.color.blue_700),
                maxLines = 1,
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            PlantItemMetric(
                value = plant.waterSpan.getEstimatedTimespan().toString() + " days",
                label = "Watering",
                Modifier.weight(1f)
            )
            PlantItemMetric(
                value = plant.temperature.toString() + "°C",
                label = "Temperature",
                Modifier.weight(1f)
            )
            PlantItemMetric(
                value = plant.sunlight,
                label = "Sunlight",
                Modifier.weight(1f)
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
    val context = LocalContext.current
    val isText = T::class.java == String::class.java
    val popupControl = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
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
        Spacer(
            modifier = Modifier
                .padding(if (isText) 8.dp else 6.dp, 0.dp)
        )
        if (isText && value is String)
            Text(
                color = colorResource(id = R.color.gray_700),
                text = value,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
            )
        else if (value is List<*>) {
            Box(
                modifier = Modifier.fillMaxHeight(),
            ) {
                IconButton(
                    onClick = { popupControl.value = true },
                    modifier = Modifier.fillMaxHeight())
                {
                    Image(
                        painter = painterResource(
                            id = context.resources.getIdentifier(
                                value.first().toString().lowercase(),
                                "drawable",
                                context.packageName
                            )
                        ),
                        contentDescription = "Watering Span",
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
            val sunlight = value.first() as SunPreference
            if (popupControl.value) {
                DescriptionPopup(popupControl, sunlight.description)
            }
        }
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

object SampleData {
    val plantsSample = listOf(
        Plant(
            "African Violet",
            PlantTimespan(3, 6),
            21,
            listOf(SunPreference.FULL_SHADE, SunPreference.FULL_SHADE),
            "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-bC6B64133c0743b34224/i-0-ymxg64133c07444a4224.jpg",
            age = 48
        ), Plant(
            "Cleistocactus",
            PlantTimespan(2, 7),
            16,
            listOf(SunPreference.FULL_SUN, SunPreference.FULL_SHADE),
            "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-kkog64133e50146a6224/i-0-rtsa64133e5014e74224.jpg"
        ), Plant(
            "White Japanese Strawberry",
            PlantTimespan(6, 8),
            11,
            listOf(SunPreference.PART_SHADE),
            "https://perenual.com/storage/marketplace/3-Whimsy%20and%20Wonder%20Seeds/p-pweY64138348e6ce81/i-0-7vjl64138348e6d801.jpg"
        )
    )
}
