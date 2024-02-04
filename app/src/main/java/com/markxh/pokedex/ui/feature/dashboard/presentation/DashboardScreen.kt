package com.markxh.pokedex.ui.feature.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.markxh.pokedex.ui.feature.dashboard.data.model.Pokemon

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val pokemonState by viewModel.pokemonState.observeAsState()

    when (pokemonState) {
        is PokemonState.Success -> {
            val pokemonList = (pokemonState as PokemonState.Success).pokemonList
            PokemonList(pokemonList)
        }

        is PokemonState.Error -> {
            val errorMessage = (pokemonState as PokemonState.Error).errorMessage
            ErrorScreen(errorMessage)
        }

        else -> LoadingScreen()
    }
}

@Composable
fun PokemonList(pokemonList: List<Pokemon>) {
    LazyColumn {
        items(pokemonList) { pokemon ->
            PokemonItem(pokemon)
        }
    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    Text(text = "Error: $errorMessage", color = Color.Red)
}

@Composable
fun LoadingScreen() {
    CircularProgressIndicator(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    )
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.Gray)
    ) {
        Text(text = pokemon.name, modifier = Modifier.align(Alignment.Center))
    }
}