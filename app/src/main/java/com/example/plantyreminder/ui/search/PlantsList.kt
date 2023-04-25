package com.example.plantyreminder.ui.search

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.plantyreminder.data.PlantSearchResult
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SubcomposeAsyncImage(
                    model = it.imageUrl,
                    contentDescription = it.names.first(),
                    modifier = Modifier
                        .weight(2f)
                        .padding(8.dp)
                        .height(80.dp)
                        .clip(CircleShape)
                ) {
                    if (painter.state is AsyncImagePainter.State.Loading ||
                        painter.state is AsyncImagePainter.State.Error
                    ) {
                        Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Photo")
                    } else SubcomposeAsyncImageContent()
                }
                Column(
                    Modifier.weight(3f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        it.names.first(),
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Center
                    )
                    it.names.drop(1).forEach {
                        Text(
                            text = it,
                            color = colorResource(id = R.color.blue_500),
                            textAlign = TextAlign.Center
                        )
                    }

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
