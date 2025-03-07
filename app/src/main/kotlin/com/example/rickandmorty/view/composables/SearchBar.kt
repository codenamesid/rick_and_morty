package com.example.rickandmorty.view.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchBar(text: String, onSearch: (String) -> Unit, modifier: Modifier) {
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
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .semantics { contentDescription = "Search Bar" },
        placeholder = { Text(text = "Search Characters") }
    )
}