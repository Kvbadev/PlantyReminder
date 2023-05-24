package com.example.plantyreminder.ui.search

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantyreminder.MainActivity
import com.example.plantyreminder.R
import com.example.plantyreminder.data.SortOption
import com.example.plantyreminder.ui.navigation.TopBar


@Composable
fun Search(
    searchViewModel: SearchViewModel,
    navigateTo: (String) -> Unit,
    openDrawer: () -> Unit,
) {

    val results by searchViewModel.results.collectAsState()
    val errorState by searchViewModel.errorState.collectAsState()
    val loading by searchViewModel.loadingState.collectAsState()
    val query = remember { searchViewModel.query }

    Column(Modifier.fillMaxSize()) {
        TopBar(buttonIcon = Icons.Filled.Menu, title = "Search", onButtonClicked = { openDrawer() })
        Row(
            Modifier
                .fillMaxWidth()
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        ) {
            SearchView(query, searchViewModel::getSearchResults, searchViewModel::sortResults)
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
            LocalContext.current, errorState!!.message, Toast.LENGTH_LONG
        ).show()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchView(
    query: MutableState<String>,
    onValueChange: () -> Unit,
    onSortChange: (SortOption) -> Unit
) {
    val isFocused = remember { mutableStateOf(false) }

    Column {

        OutlinedTextField(
            value = query.value,
            onValueChange = {
                query.value = it
                onValueChange()
            },
            textStyle = TextStyle(fontSize = 16.sp),
            leadingIcon = {
                if (isFocused.value) Icon(
                    Icons.Filled.Search,
                    null,
                    Modifier,
                    colorResource(id = R.color.green_700)
                )
                else Icon(Icons.Filled.Search, null, Modifier, Color.Gray)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
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

        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember { mutableStateOf(SortOption.NONE) }

        if (query.value != "") {
            Box(
                Modifier
                    .padding(12.dp, 0.dp, 12.dp, 12.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {

                ExposedDropdownMenuBox(
                    expanded = expanded, onExpandedChange = {
                        expanded = !expanded
                    }, modifier = Modifier
                        .width(120.dp)
                ) {
                    Text(
                        text = selectedText.text,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(colorResource(id = R.color.gray_200))
                            .padding(16.dp)
                            .width(120.dp),
                        textAlign = TextAlign.Center
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    ) {
                        SortOption.values().forEach {
                            DropdownMenuItem(onClick = {
                                selectedText = it
                                expanded = !expanded
                                onSortChange(selectedText)
                            }) {
                                Text(text = it.text, modifier = Modifier)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchPreview() {
    val text = remember { mutableStateOf("a") }
    SearchView(text, {}) {}
}
