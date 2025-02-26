package com.example.rickandmorty.view.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmorty.model.data.Character
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailView(
    navController: NavController, character: Character,
    modifier: Modifier = Modifier
) {
    val savedCharacter = rememberSaveable { character }
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .semantics { contentDescription = "Character Detail View" }
    ){
        TopAppBar(
            title = { Text("Details") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        Column(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = savedCharacter.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            AsyncImage(
                model = savedCharacter.image,
                contentDescription = savedCharacter.name,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Text(text = "Species: ${savedCharacter.species}")
            Text(text = "Status: ${savedCharacter.status}")
            Text(text = "Origin: ${savedCharacter.origin.name}")
            if (savedCharacter.type.isNotEmpty()) {
                Text(text = "Type: ${savedCharacter.type}")
            }
            Text(text = "Created: ${formatDate(savedCharacter.created)}")
        }
    }
}

fun formatDate(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
    val dateTime = ZonedDateTime.parse(dateString, formatter)
    val outputFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.getDefault())
    return dateTime.format(outputFormatter)
}