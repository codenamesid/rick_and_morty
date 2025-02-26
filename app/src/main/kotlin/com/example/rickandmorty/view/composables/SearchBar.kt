package com.example.rickandmorty.view.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material3.Text

@Composable
fun SearchBar(text: String, onSearch: (String) -> Unit) {
    val searchText = remember { mutableStateOf(text) }
    var searchJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()

    TextField(
        value = searchText.value,
        onValueChange = {
            searchText.value = it
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                delay(1000) // debounce time in milliseconds
                onSearch(searchText.value)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        placeholder = { Text(text = "Search Characters") }
    )
}