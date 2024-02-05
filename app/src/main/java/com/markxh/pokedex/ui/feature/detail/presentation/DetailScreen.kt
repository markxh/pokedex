package com.markxh.pokedex.ui.feature.detail.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.markxh.pokedex.R
import com.markxh.pokedex.ui.feature.detail.domain.model.PokemonDetails
import com.markxh.pokedex.ui.shared.ErrorScreen
import com.markxh.pokedex.ui.shared.LoadingScreen
import com.markxh.pokedex.ui.shared.TopAppBar

@Composable
fun DetailScreen(
    navController: NavController,
    name: String,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.observeAsState()

    when (state) {
        is PokemonDetailsState.Success -> {
            PokemonDetailsScreen((state as PokemonDetailsState.Success).pokemonDetails)
        }

        is PokemonDetailsState.Error -> {
            val errorMessage = (state as PokemonDetailsState.Error).errorMessage
            ErrorScreen(errorMessage)
        }

        else -> LoadingScreen()
    }

    viewModel.getPokemonDetails(name)
}

@Composable
fun PokemonDetailsScreen(pokemonDetails: PokemonDetails) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        TopAppBar(
            title = pokemonDetails.name,
            navigationIcon = Icons.Default.ArrowBack,
            actions = {
                //todo
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.size_medium)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PokemonImage(imageUrl = pokemonDetails.image)
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_medium)))
            PokemonStat(value = "Height: ${pokemonDetails.height}")
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.size_small)))
            PokemonStat(value = "Weight: ${pokemonDetails.weight}")
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PokemonImage(imageUrl: String) {
    Box(
        modifier = Modifier
            .size(dimensionResource(R.dimen.image_size))
            .background(Color.White)
    ) {
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = "Pokemon Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun PokemonStat(value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = value)
    }
}