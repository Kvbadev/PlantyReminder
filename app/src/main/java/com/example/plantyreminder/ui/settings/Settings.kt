package com.example.plantyreminder.ui.settings

import com.example.plantyreminder.ui.details.DetailsViewModel

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import coil.request.ImageRequest
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.R
import com.example.plantyreminder.domain.Plant
import com.example.plantyreminder.domain.UiEvent
import com.example.plantyreminder.ui.AsyncImageHandler
import com.example.plantyreminder.ui.ExtendableText
import com.example.plantyreminder.ui.FullScreenLoader
import com.example.plantyreminder.ui.PlantyScreen
import com.example.plantyreminder.ui.home.SampleData
import com.example.plantyreminder.ui.navigation.TopBar
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Settings(navigateBack: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        TopBar(
            buttonIcon = Icons.Filled.ArrowBack,
            title = "Settings",
            onButtonClicked = { navigateBack() }
        )
        Text("Settings")
    }
}

@Composable
@Preview(showBackground = true)
private fun SettingsPreview() {
    Settings() {}
}
