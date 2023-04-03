@file:OptIn(
    ExperimentalFoundationApi::class
)

package com.example.plantyreminder.views.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.example.plantyreminder.R
import com.example.plantyreminder.data.models.Plant
import com.example.plantyreminder.data.models.PlantTimespan
import com.example.plantyreminder.data.models.SunPreference
import java.util.*

@Composable
fun PlantSlider(plants: List<Plant>) {
    HorizontalPager(
        pageCount = plants.size, contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 0.dp)
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
        modifier = Modifier.padding(10.dp, 0.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(Modifier.height(900.pxToDp())) {
            AsyncImage(
                model = plant.imageUrl,
                contentDescription = "Plant's image",
                Modifier
                    .align(Alignment.Center)
                    .clip(
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp),
                    )
            )
        }
        Text(
            text = plant.name,
            Modifier
                .padding(0.dp, 10.dp)
                .width(IntrinsicSize.Max),
            color = colorResource(id = R.color.blue_700),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
        ) {
            PlantItemMetric(
                value = plant.waterSpan.getEstimatedTimespan().toString() + " days",
                label = "Watering"
            )
            PlantItemMetric(
                value = plant.temperature.toString() + "°C",
                label = "Temperature"
            )
            PlantItemMetric(
                value = plant.sunlight,
                label = "Sunlight"
            )
        }
    }
}

@Composable
inline fun <reified T> PlantItemMetric(value: T, label: String) {
    val context = LocalContext.current;
    val isText = T::class.java == String::class.java;
    var popupControl by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
            color = colorResource(id = R.color.gray_500),
            textAlign = TextAlign.Start,
        )
        Spacer(
            modifier = Modifier
                .padding(if (isText) 8.dp else 6.dp)
        )
        if (isText && value is String)
            Text(
                color = colorResource(id = R.color.gray_700),
                text = value,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
            )
        else if (value is List<*>) {
            val sunlight = value.first() as SunPreference
            Image(
                painter = painterResource(
                    id = context.resources.getIdentifier(
                        value.first().toString().lowercase(),
                        "drawable",
                        context.packageName
                    )
                ),
                contentDescription = "Watering Span",
                modifier = Modifier
                    .clickable {
                        popupControl = true
                    }

            )
            if (popupControl) {
                Popup(
                    offset = IntOffset(-8, 60),
                    properties = PopupProperties(
                        focusable = false,
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true,
                        clippingEnabled = true,
                    ),
                    onDismissRequest = { popupControl = false },
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
                            text = sunlight.description,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(6.dp),
                            color = colorResource(id = R.color.black),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
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
            "https://perenual.com/storage/marketplace/4-Le%20Jardin%20Nordique/p-bC6B64133c0743b34224/i-0-ymxg64133c07444a4224.jpg"
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
