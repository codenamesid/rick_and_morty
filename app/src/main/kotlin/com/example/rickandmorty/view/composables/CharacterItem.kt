package com.example.rickandmorty.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.rickandmorty.model.data.Character

@Composable
fun CharacterItem(character: Character, onClick: (Character) -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier.clickable { onClick(character) }
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name
        )
        Text(text = character.name)
    }
}