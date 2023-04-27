package com.example.plantyreminder.ui.search

import android.media.ImageReader
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.transform.Transformation
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.ui.home.SampleData
import com.example.plantyreminder.R
import java.util.*

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
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(Modifier.weight(2f).padding(4.dp, 2.dp)) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = it.names.first(),
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        loading = {
                            Box(Modifier.size(64.dp)) {
                                CircularProgressIndicator(
                                    Modifier
                                        .align(Alignment.Center)
                                        .size(32.dp)
                                )
                            }
                        },
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(6f)
                ) {
                    Text(
                        it.names.first().replaceFirstChar { it.uppercaseChar() },
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = it.names.joinToString("\n"),
                        color = colorResource(id = R.color.blue_500),
                        fontSize = 10.sp,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun PlantSliderPreview() {
    PlantsList(SampleData.searchResultSample);
}
