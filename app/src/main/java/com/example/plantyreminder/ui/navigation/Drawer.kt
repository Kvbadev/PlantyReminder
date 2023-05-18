package com.example.plantyreminder.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class DrawerScreens(val displayName: String) {
    Home("Home"), Search("Add plant"), Settings("Settings")
}

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DrawerScreens.values().forEach { screen ->
            Text(
                text = screen.displayName,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .clickable {
                        onDestinationClicked(screen.name)
                    }
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    Drawer() {}
}
