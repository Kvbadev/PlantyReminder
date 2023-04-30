package com.example.plantyreminder.ui.search

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.lifecycle.ViewModel
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.R
import com.example.plantyreminder.ui.home.SampleData
import com.example.plantyreminder.utils.debounce
import org.koin.androidx.compose.getViewModel


@Composable
fun Search(
    onDetailsPopUp: () -> Unit,
    onBackButton: () -> Unit
) {
    val searchViewModel = getViewModel<SearchViewModel>()
    val results by searchViewModel.results.collectAsState()
    val errorState by searchViewModel.errorState.collectAsState()
    val loading by searchViewModel.loadingState.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .fillMaxWidth()
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        ) {
            Box(Modifier.clickable {
                onDetailsPopUp()
            }.width(48.dp).fillMaxHeight()) {
                Icon(
                    Icons.Filled.ArrowBack, "",
                    modifier = Modifier.align(Alignment.Center).size(32.dp)
                )
            }
            SearchView(searchViewModel::getSearchResults)
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
        } else PlantsList(plants = results, onDetailsPopUp = onDetailsPopUp)
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
fun SearchView(onValueChange: (value: String) -> Unit) {
    var value by remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
            onValueChange(value)
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
    SearchView {}
}