package com.example.plantyreminder.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.plantyreminder.data.PlantSearchResult
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.AsyncImageHandler
import com.example.plantyreminder.ui.PlantyScreen

@Composable
fun PlantsList(
    plants: List<PlantSearchResult>,
    onDetailsPopUp: (String) -> Unit
) {
    LazyColumn(Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)) {

        items(plants) { searchResult ->
            val itemName = searchResult.names.first().replaceFirstChar { it.uppercaseChar() }.trim()
            val otherNames = searchResult.names.joinToString("\n")

            Row(
                Modifier
                    .clickable {
                        onDetailsPopUp("${PlantyScreen.Details.name}/${searchResult.id}")
                    }
                    .fillMaxWidth()
                    .padding(2.dp, 10.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    Modifier
                        .weight(2f)
                        .padding(0.dp, 2.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(searchResult.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = searchResult.names.first(),
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop
                    ) {
                        AsyncImageHandler(subcomposeAsyncImageScope = this, boxSize = 64.dp)
                    }
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(6f)
                ) {
                    Text(
                        itemName,
                        color = colorResource(id = R.color.black),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = otherNames,
                        color = colorResource(id = R.color.blue_500),
                        fontSize = 10.sp,
                        maxLines = 4,
                    )
                }

            }
        }
    }
}
