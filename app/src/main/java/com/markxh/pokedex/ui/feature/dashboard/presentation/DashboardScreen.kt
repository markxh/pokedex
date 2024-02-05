package com.markxh.pokedex.ui.feature.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markxh.pokedex.R
import com.markxh.pokedex.core.navigation.Route
import com.markxh.pokedex.ui.feature.dashboard.domain.model.Pokemon
import com.markxh.pokedex.ui.shared.ErrorScreen
import com.markxh.pokedex.ui.shared.LoadingScreen
import com.markxh.pokedex.ui.shared.ToolbarAction
import com.markxh.pokedex.ui.shared.TopAppBar

@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel = hiltViewModel()) {
    val pokemonState by viewModel.pokemonState.observeAsState()

    when (pokemonState) {
        is PokemonState.Success -> {
            val pokemonList = (pokemonState as PokemonState.Success).pokemonList
            PokemonListScreen(pokemonList) {
                navController.navigate("${Route.DETAIL}/${it.name}") {}
            }
        }

        is PokemonState.Error -> {
            val errorMessage = (pokemonState as PokemonState.Error).errorMessage
            ErrorScreen(errorMessage)
        }

        else -> LoadingScreen()
    }
}

@Composable
fun PokemonListScreen(pokemonList: List<Pokemon>, onclick: (pokemon: Pokemon) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    Column {
        TopAppBar(
            title = stringResource(R.string.app_name),
            navigationIcon = Icons.Default.Menu,
            actions = {
                ToolbarAction(Icons.Default.Search) {
                    searchQuery = ""
                }
            }
        )

        //todo add search

        LazyColumn {
            items(pokemonList) { PokemonItem(it, onclick) }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onclick: (pokemon: Pokemon) -> Unit) {
    Box(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.size_medium))
            .fillMaxWidth()
            .clickable { onclick(pokemon) }
    ) {
        Text(
            text = pokemon.name,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = dimensionResource(R.dimen.size_small))
        )
    }
}