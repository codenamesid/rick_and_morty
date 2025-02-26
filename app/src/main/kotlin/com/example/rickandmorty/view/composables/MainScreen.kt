package com.example.rickandmorty.view.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.viewmodel.CharacterViewModel
import com.example.rickandmorty.viewmodel.LoadingState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: CharacterViewModel) {
    val characters by viewModel.characters.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()
    val navController = rememberNavController()
    val selectedCharacter = remember { mutableStateOf<Character?>(null) }
    val searchQuery = rememberSaveable { mutableStateOf("") }

    Column {
        NavHost(navController = navController, startDestination = "characterGrid") {
            composable("characterGrid") {
                Column {
                    TopAppBar(
                        title = { Text("Rick and Morty Search") },
                        modifier = Modifier.padding(top = 24.dp)
                    )
                    SearchBar(searchQuery.value, onSearch = { name ->
                        searchQuery.value = name
                        viewModel.searchCharacters(name)
                    })
                    when (loadingState) {
                        is LoadingState.Initialize -> {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(text = "Search for your favorite character")
                            }
                        }

                        is LoadingState.Loading -> {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is LoadingState.Success -> {
                            CharacterGrid(characters = characters, onItemClick = { character ->
                                selectedCharacter.value = character
                                navController.navigate("characterDetail")
                            })
                        }

                        is LoadingState.Error -> {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(text = (loadingState as LoadingState.Error).message)
                            }
                        }
                    }
                }
            }
            composable("characterDetail") {
                selectedCharacter.value?.let {
                    CharacterDetailView(navController = navController, character = it)
                }
            }
        }
    }
}