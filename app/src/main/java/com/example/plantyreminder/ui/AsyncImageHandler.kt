package com.example.plantyreminder.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.SubcomposeAsyncImageScope

@Composable
fun AsyncImageHandler(subcomposeAsyncImageScope: SubcomposeAsyncImageScope, boxSize: Dp) {
    when (subcomposeAsyncImageScope.painter.state) {
        is AsyncImagePainter.State.Loading -> {
            Box(Modifier.size(boxSize)) {
                CircularProgressIndicator(
                    Modifier
                        .align(Alignment.Center)
                        .size(boxSize/2)
                )
            }
        }
        is AsyncImagePainter.State.Error -> {
            Box(Modifier.size(boxSize)) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        else -> {
            subcomposeAsyncImageScope.SubcomposeAsyncImageContent()
        }
    }
}