package com.example.rickandmorty.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.model.data.Character

@Composable
fun CharacterGrid(
    characters: List<Character>,
    onItemClick: (Character) -> Unit,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.semantics { contentDescription = "Character Grid" }
    ) {
        items(characters) { character ->
            CharacterItem(
                character = character,
                onClick = { onItemClick(character) },
                modifier = Modifier.semantics {
                    contentDescription = "Character Item: ${character.name}"
                }
            )
        }
    }
}
