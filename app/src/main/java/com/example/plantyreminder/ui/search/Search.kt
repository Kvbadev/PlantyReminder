package com.example.plantyreminder.ui.search

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.PlantyScreen
import com.example.plantyreminder.ui.navigation.TopBar
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


@Composable
fun Search(
    navigateTo: (String) -> Unit,
    openDrawer: () -> Unit
) {
    val searchViewModel: SearchViewModel = koinViewModel()

    val results by searchViewModel.results.collectAsState()
    val errorState by searchViewModel.errorState.collectAsState()
    val loading by searchViewModel.loadingState.collectAsState()
    val query = remember { searchViewModel.query }

    Column(Modifier.fillMaxSize()) {
        TopBar(
            buttonIcon = Icons.Filled.Menu,
            title = "Search",
            onButtonClicked = { openDrawer() }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        ) {
            SearchView(query, searchViewModel::getSearchResults)
        }
        if (loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .align(Alignment.Center),
                    color = colorResource(
                        id = R.color.blue_700
                    )
                )
            }
        } else PlantsList(plants = results, onDetailsPopUp = navigateTo)
    }

    if (errorState != null && MainActivity.isActivityVisible) {
        Toast.makeText(
            LocalContext.current,
            errorState!!.message,
            Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
fun SearchView(query: MutableState<String>, onValueChange: () -> Unit) {
    val isFocused = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = query.value,
        onValueChange = {
            query.value = it
            onValueChange()
        },
        textStyle = TextStyle(fontSize = 16.sp),
        leadingIcon = {
            if (isFocused.value)
                Icon(Icons.Filled.Search, null, Modifier, colorResource(id = R.color.green_700))
            else
                Icon(Icons.Filled.Search, null, Modifier, Color.Gray)

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(2.dp, colorResource(id = R.color.green_700), RoundedCornerShape(12.dp))
            .onFocusChanged {
                isFocused.value = it.isFocused
            },
        placeholder = { Text(text = "Cactus") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(id = R.color.black),
            backgroundColor = Color.Transparent,
            cursorColor = colorResource(id = R.color.gray_500),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        )
    )
}

@Preview
@Composable
fun SearchPreview() {
    val text = remember { mutableStateOf("") }
    SearchView(text) {}
}
