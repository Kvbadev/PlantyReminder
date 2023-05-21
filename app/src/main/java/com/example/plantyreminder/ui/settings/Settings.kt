package com.example.plantyreminder.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plantyreminder.ui.navigation.TopBar

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
